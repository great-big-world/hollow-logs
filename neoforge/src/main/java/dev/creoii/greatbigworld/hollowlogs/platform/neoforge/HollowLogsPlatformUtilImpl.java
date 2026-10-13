package dev.creoii.greatbigworld.hollowlogs.platform.neoforge;

import dev.creoii.greatbigworld.hollowlogs.neoforge.HollowLogsDataAttachmentsNeoForge;
import net.minecraft.world.entity.player.Player;

public final class HollowLogsPlatformUtilImpl {
    public static boolean getPlayerEnteringHollowLog(Player player) {
        return player.hasData(HollowLogsDataAttachmentsNeoForge.PLAYER_ENTERING_HOLLOW_LOG) && player.getData(HollowLogsDataAttachmentsNeoForge.PLAYER_ENTERING_HOLLOW_LOG);
    }

    public static void setPlayerEnteringHollowLog(Player player, boolean entering) {
        player.setData(HollowLogsDataAttachmentsNeoForge.PLAYER_ENTERING_HOLLOW_LOG, entering);
    }

    public static void removePlayerEnteringHollowLog(Player player) {
        player.removeData(HollowLogsDataAttachmentsNeoForge.PLAYER_ENTERING_HOLLOW_LOG);
    }

}
