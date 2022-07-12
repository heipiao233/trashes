package net.heipiao.trashes.blocks.entities;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import net.heipiao.trashes.blocks.BinBlock;
import net.heipiao.trashes.blocks.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityBuilder<BinBlockEntity, BlockBuilder<BinBlock, Registrate>> BIN = ModBlocks.BIN
            .blockEntity((type, pos, state)->new BinBlockEntity(pos, state));
    public static void register(){
        BIN.register();
    }
}
