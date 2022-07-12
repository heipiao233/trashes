package net.heipiao.trashes.blocks;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import net.heipiao.trashes.Trashes;

public class ModBlocks {
    public static final BlockBuilder<BinBlock, Registrate> BIN = Trashes
            .getInstance()
            .registrate
            .block("bin", BinBlock::new)
            .defaultLoot();
    public static void register(){
        BIN.register();
    }
}
