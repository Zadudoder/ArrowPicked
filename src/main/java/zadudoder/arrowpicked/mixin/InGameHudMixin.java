package zadudoder.arrowpicked.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zadudoder.arrowpicked.ArrowChecker;
import zadudoder.arrowpicked.ArrowPicked;
import zadudoder.arrowpicked.config.ArrowPickedConfig;


@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    private static final int ARROW_HOTBAR_SIZE = 16;
    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MatrixStack matrix = context.getMatrices();
        matrix.push();
        if (this.client.options.hudHidden || this.client.world == null || this.client.player == null) return;
        if (ArrowPickedConfig.get().modEnabled) {

            ItemStack arrow = ArrowChecker.getLoadedArrow();
            if (arrow.isEmpty()) return;

            ArrowPickedConfig config = ArrowPicked.CONFIG;
            int screenWidth = context.getScaledWindowWidth();
            int screenHeight = context.getScaledWindowHeight();
            int size = config.arrowSize;

            int x = config.getCalculatedX(screenWidth, size);
            int y = config.getCalculatedY(screenHeight, size);

            MatrixStack matrices = context.getMatrices();
            matrices.push();
            matrices.translate(x, y, 0);
            float scale = size / 16f;
            matrices.scale(scale, scale, 1f);
            context.drawItem(arrow, 0, 0);
            matrix.pop();
        }
    }
}