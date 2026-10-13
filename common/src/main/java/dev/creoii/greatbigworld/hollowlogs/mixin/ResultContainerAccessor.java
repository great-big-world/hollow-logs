package dev.creoii.greatbigworld.hollowlogs.mixin;

import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ResultContainer.class)
public interface ResultContainerAccessor {
    @Accessor(value = "recipeUsed")
    RecipeHolder<?> getRecipeUsed();
}
