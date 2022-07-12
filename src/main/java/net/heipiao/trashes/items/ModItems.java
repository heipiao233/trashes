package net.heipiao.trashes.items;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.heipiao.trashes.Trashes;
import net.heipiao.trashes.blocks.BinBlock;
import net.heipiao.trashes.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItems {
    public static final NonNullSupplier<CreativeModeTab> TAB = ()->new CreativeModeTab("trashes") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.BIN.getEntry());
        }
    };
    public static final ItemBuilder<BlockItem, BlockBuilder<BinBlock, Registrate>> BIN = ModBlocks.BIN.item()
            .tab(TAB)
            .model((context, provider)-> provider.blockItem(ModBlocks.BIN::getEntry));
    public static final ItemBuilder<TrashBagItem, Registrate> TRASH_BAG = Trashes.getInstance().registrate
            .item("trash_bag", TrashBagItem::new)
            .tab(TAB)
            .model((context, provider)->provider.basicItem(context.get()))
            .properties(properties -> properties.stacksTo(1));
    public static void register(){
        BIN.register();
        TRASH_BAG.register();
    }
}
