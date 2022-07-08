package net.heipiao.trashes;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod("trashes")
public class Trashes
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public Trashes()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
    }
}
