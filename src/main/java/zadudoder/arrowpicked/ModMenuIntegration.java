package zadudoder.arrowpicked;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import zadudoder.arrowpicked.config.ArrowPickedConfig;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ArrowPickedConfig config = AutoConfig.getConfigHolder(ArrowPickedConfig.class).getConfig();
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.arrowpicked.config"))
                    .setSavingRunnable(() -> AutoConfig.getConfigHolder(ArrowPickedConfig.class).save());

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.arrowpicked.general"));

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.arrowpicked.modEnabled"), config.modEnabled)
                    .setSaveConsumer(newValue -> config.modEnabled = newValue)
                    .build());

            // Позиция по X
            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.arrowpicked.posX"), config.arrowPosX, 1, 100)
                    .setTextGetter(value -> Text.literal(value + " %"))
                    .setSaveConsumer(newValue -> config.arrowPosX = newValue)
                    .build());

            // Позиция по Y
            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.arrowpicked.posY"), config.arrowPosY, 1, 100)
                    .setTextGetter(value -> Text.literal(value + " %"))
                    .setSaveConsumer(newValue -> config.arrowPosY = newValue)
                    .build());

            // Размер иконки
            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.arrowpicked.size"), config.arrowSize, 4, 64)
                    .setTextGetter(value -> Text.translatable("text.arrowpicked.pixels", value))
                    .setSaveConsumer(newValue -> config.arrowSize = newValue)
                    .build());

            return builder.build();
        };
    }
}