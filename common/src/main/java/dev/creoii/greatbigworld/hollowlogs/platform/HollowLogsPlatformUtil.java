package dev.creoii.greatbigworld.hollowlogs.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.player.Player;

public final class HollowLogsPlatformUtil {
    @ExpectPlatform
    public static void setPlayerEnteringHollowLog(Player player, boolean entering) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean getPlayerEnteringHollowLog(Player player) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void removePlayerEnteringHollowLog(Player player) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean allowLogAxeCraftingRecipe() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setAllowLogAxeCraftingRecipe(boolean allowLogAxeCraftingRecipe) {
        throw new AssertionError();
    }
}
