package reinashsl.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import reinashsl.C204Launcher;

@SpirePatch(
        clz = EndTurnButton.class,
        method = "update"
)
public class OnEndTurnPatch {
    @SpireInsertPatch(
            rloc = 41
    )
    public static void onEndTurn() {
        C204Launcher.replayRecorder.onEndTurn();
    }
}
