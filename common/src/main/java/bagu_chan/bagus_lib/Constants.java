package bagu_chan.bagus_lib;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "bagus_lib";
	public static final String MOD_NAME = "Bagus Lib";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static ResourceLocation prefix(String string) {
        return new ResourceLocation(MOD_ID, string);
    }
}