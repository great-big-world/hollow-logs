package dev.creoii.greatbigworld.hollowlogs.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ResultContainer.class)
public class ResultContainerMixin {
    @Shadow
    @Final
    private NonNullList<ItemStack> itemStacks;

    @Shadow
    private @Nullable RecipeHolder<?> recipeUsed;

    @Inject(method = "removeItem", at = @At("HEAD"))
    private void gbw$test(int slot, int count, CallbackInfoReturnable<ItemStack> cir) {
        if (recipeUsed != null) {
            System.out.println("removed: " + itemStacks.getFirst() + " from recipe: " + recipeUsed.id().identifier());
        }
    }

    @Inject(method = "clearContent", at = @At("HEAD"))
    private void gbw$testClear(CallbackInfo ci) {
        if (recipeUsed != null) {
            System.out.println("cleared: " + itemStacks.getFirst() + " from recipe: " + recipeUsed.id().identifier());
        }
    }
}
