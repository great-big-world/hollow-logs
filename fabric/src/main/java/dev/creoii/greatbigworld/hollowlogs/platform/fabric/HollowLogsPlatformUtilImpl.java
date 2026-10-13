package dev.creoii.greatbigworld.hollowlogs.platform.fabric;

import dev.creoii.greatbigworld.hollowlogs.fabric.HollowLogsDataAttachmentsFabric;
import dev.creoii.greatbigworld.hollowlogs.fabric.HollowLogsFabric;
import net.minecraft.world.entity.player.Player;

public final class HollowLogsPlatformUtilImpl {
    public static boolean getPlayerEnteringHollowLog(Player player) {
        return player.getAttachedOrElse(HollowLogsDataAttachmentsFabric.PLAYER_ENTERING_HOLLOW_LOG, false);
    }

    public static void setPlayerEnteringHollowLog(Player player, boolean entering) {
        player.setAttached(HollowLogsDataAttachmentsFabric.PLAYER_ENTERING_HOLLOW_LOG, entering);
    }

    public static void removePlayerEnteringHollowLog(Player player) {
        player.removeAttached(HollowLogsDataAttachmentsFabric.PLAYER_ENTERING_HOLLOW_LOG);
    }

    public static boolean allowLogAxeCraftingRecipe() {
        return HollowLogsFabric.CONFIG == null || HollowLogsFabric.CONFIG.allowLogAxeCraftingRecipe;
    }

    public static void setAllowLogAxeCraftingRecipe(boolean allowLogAxeCraftingRecipe) {
        if (HollowLogsFabric.CONFIG != null) HollowLogsFabric.CONFIG.allowLogAxeCraftingRecipe = allowLogAxeCraftingRecipe;
    }
}
