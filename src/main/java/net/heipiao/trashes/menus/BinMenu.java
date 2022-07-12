package net.heipiao.trashes.menus;

import net.heipiao.trashes.blocks.entities.BinBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BinMenu extends AbstractContainerMenu {
    private final Container container;
    public BinMenu(int id, Inventory playerInventory, BlockPos pos, Level world, ContainerData intArray) {
        super(ModMenuTypes.BIN.getEntry(), id);
        this.container=((BinBlockEntity)world.getBlockEntity(pos)).getContainer();
        addSlot(new Slot(container, 0, 80, 32));
        layoutPlayerInventorySlots(playerInventory, 8, 84);
    }

    private void layoutPlayerInventorySlots(Container inventory, int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    private int addSlotRange(Container inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private void addSlotBox(Container inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return container.stillValid(p_38874_);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_38942_);
        if (slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (p_38942_ < 1) {
                if (!this.moveItemStackTo(itemStack1, 0, 1, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }
}
