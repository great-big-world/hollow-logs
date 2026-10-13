package dev.creoii.greatbigworld.hollowlogs.mixin;

import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractCraftingMenu.class)
public interface AbstractCraftingMenuAccessor {
    @Accessor(value = "craftSlots")
    CraftingContainer getCraftSlots();

    @Accessor(value = "resultSlots")
    ResultContainer getResultSlots();
}
