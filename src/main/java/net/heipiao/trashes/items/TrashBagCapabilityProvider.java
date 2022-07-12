package net.heipiao.trashes.items;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrashBagCapabilityProvider implements ICapabilityProvider {
    private final SimpleContainer container;
    public TrashBagCapabilityProvider(ItemStack bag){
        container=new SimpleContainer(9);
        CompoundTag tag=bag.getTag();
        if(tag!=null)
            container.fromTag(tag.getList("items", Tag.TAG_LIST));
        container.addListener(inv -> bag.getOrCreateTag().put("items", container.createTag()));
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(()-> new InvWrapper(container)).cast();
        }
        return LazyOptional.empty();
    }
}
