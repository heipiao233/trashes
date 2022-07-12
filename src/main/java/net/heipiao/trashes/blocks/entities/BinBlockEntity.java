package net.heipiao.trashes.blocks.entities;

import net.heipiao.trashes.menus.BinMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.CapabilityItemHandler;

public class BinBlockEntity extends BlockEntity implements MenuProvider {
    private final ContainerData data=new SimpleContainerData(0);
    private final SimpleContainer container=new SimpleContainer(1);
    public BinBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.BIN.getEntry(), p_155229_, p_155230_);
        container.addListener((container)->this.setChanged());
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.put("item", container.getItem(0).save(new CompoundTag()));
    }

    @Override
    public void load(CompoundTag p_155245_) {
        container.setItem(0, ItemStack.of(p_155245_.getCompound("item")));
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.trashes.bin");
    }

    @Override
    public AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_, Player player) {
        return new BinMenu(p_58627_, p_58628_, getBlockPos(), level, data);
    }
    public Container getContainer(){
        return container;
    }
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BinBlockEntity pBlockEntity){
        if(!pLevel.isClientSide){
            pLevel.getEntities(null, new AABB(pPos.above())).forEach(entity -> {
                if(entity instanceof ItemEntity ie){
                    var cap=pBlockEntity.container.getItem(0).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                    cap.ifPresent(itemHandler -> {
                        var slots=itemHandler.getSlots();
                        for (int i = 0; i < slots; i++) {
                            ie.setItem(itemHandler.insertItem(i, ie.getItem(), false));
                        }
                    });
                }
            });
        }
    }
}
