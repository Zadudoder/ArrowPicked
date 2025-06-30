package zadudoder.arrowpicked.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "arrowpicked")
public class ArrowPickedConfig implements ConfigData {
    public boolean modEnabled = true;
    public int arrowPosX = 48;
    public int arrowPosY = 50;
    public int arrowSize = 16;

    public static ArrowPickedConfig get() {
        return AutoConfig.getConfigHolder(ArrowPickedConfig.class).getConfig();
    }

    public int getCalculatedX(int screenWidth, int elementWidth) {
        return (int) ((arrowPosX / 100f) * (screenWidth - elementWidth));
    }

    public int getCalculatedY(int screenHeight, int elementHeight) {
        return (int) ((arrowPosY / 100f) * (screenHeight - elementHeight));
    }
}