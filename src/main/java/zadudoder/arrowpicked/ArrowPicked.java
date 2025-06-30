package zadudoder.arrowpicked;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zadudoder.arrowpicked.config.ArrowPickedConfig;

public class ArrowPicked implements ModInitializer {
	public static final String MOD_ID = "arrowpicked";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ArrowPickedConfig CONFIG;

	@Override
	public void onInitialize() {
		AutoConfig.register(ArrowPickedConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(ArrowPickedConfig.class).getConfig();
		LOGGER.info("Arrow is Picked!");
	}
}