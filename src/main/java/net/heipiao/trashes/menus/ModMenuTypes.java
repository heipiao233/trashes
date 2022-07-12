package net.heipiao.trashes.menus;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.MenuBuilder;
import net.heipiao.trashes.Trashes;
import net.heipiao.trashes.menus.screens.BinScreen;
import net.minecraft.world.inventory.SimpleContainerData;

public class ModMenuTypes {
    public static final MenuBuilder<BinMenu, BinScreen, Registrate> BIN = Trashes.getInstance().registrate
            .menu("bin", (type, windowId, inv, buf) -> new BinMenu(windowId, inv, buf.readBlockPos(), inv.player.level, new SimpleContainerData(2)), ()->BinScreen::new);
    public static void register(){
        BIN.register();
    }
}
