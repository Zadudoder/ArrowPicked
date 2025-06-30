package zadudoder.arrowpicked;

import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.util.collection.DefaultedList;

public class ArrowChecker {
    public static ItemStack getLoadedArrow() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return ItemStack.EMPTY;

        PlayerInventory inventory = client.player.getInventory();
        DefaultedList<ItemStack> main = inventory.main;

        for (int i = 0; i < 9; i++) {
            ItemStack stack = main.get(i);
            if (stack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(stack)) {
                // Альтернативный способ для версий, где getItems() недоступен
                ChargedProjectilesComponent projectiles = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
                if (projectiles != null) {
                    // В некоторых версиях снаряды хранятся в List<ItemStack> внутри компонента
                    for (ItemStack projectile : projectiles.getProjectiles()) {
                        if (!projectile.isEmpty()) return projectile;
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }
}