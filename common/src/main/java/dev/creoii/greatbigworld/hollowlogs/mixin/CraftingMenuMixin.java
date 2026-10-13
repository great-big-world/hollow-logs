package dev.creoii.greatbigworld.hollowlogs.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;onQuickCraft(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V"))
    private void gbw$test(Player player, int slotIndex, CallbackInfoReturnable<ItemStack> cir, @Local(name = "clicked") ItemStack clicked) {
        if (this instanceof AbstractCraftingMenuAccessor accessor) {
            CraftingContainer craftSlots = accessor.getCraftSlots();
            ResultContainer resultSlots = accessor.getResultSlots();
            if (resultSlots instanceof ResultContainerAccessor accessor1 && accessor1.getRecipeUsed() != null) {
                System.out.println("quick crafted " + clicked.getItem().arch$registryName() + " from recipe " + accessor1.getRecipeUsed().id().identifier());

                craftSlots.getItems().forEach(itemStack -> {
                    if (itemStack.is(ItemTags.AXES)) {
                        itemStack.hurtAndBreak(1, player, player.getUsedItemHand());
                    }
                });
            }
        }
    }
}
