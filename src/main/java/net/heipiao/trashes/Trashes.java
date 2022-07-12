package net.heipiao.trashes;

import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import net.heipiao.trashes.blocks.ModBlocks;
import net.heipiao.trashes.blocks.entities.ModBlockEntities;
import net.heipiao.trashes.items.ModItems;
import net.heipiao.trashes.menus.ModMenuTypes;
import net.heipiao.trashes.menus.screens.BinScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod("trashes")
public class Trashes
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public final Registrate registrate = Registrate.create("trashes");
    private static Trashes instance;

    public static Trashes getInstance(){
        return instance;
    }

    public Trashes()
    {
        MinecraftForge.EVENT_BUS.register(this);
        instance=this;
        ModBlocks.register();
        ModItems.register();
        ModBlockEntities.register();
        ModMenuTypes.register();
        registrate.addRawLang("item.trashes.trash_bag.disgusting", "The trash bag is too disgusting.");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onClientStartup(FMLClientSetupEvent event){

        }
    }
}
