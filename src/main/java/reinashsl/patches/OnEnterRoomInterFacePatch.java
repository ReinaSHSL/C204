package reinashsl.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import reinashsl.C204Launcher;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "nextRoomTransition",
        paramtypez  = {
                SaveFile.class
        }
)
public class OnEnterRoomInterFacePatch {
    public static void Prefix(AbstractDungeon __instance, SaveFile useless) {
        C204Launcher.replayRecorder.receiveRoomEntry(AbstractDungeon.nextRoom);
    }
}
