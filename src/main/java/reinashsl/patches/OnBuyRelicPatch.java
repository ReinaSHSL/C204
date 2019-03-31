package reinashsl.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StoreRelic;
import reinashsl.C204Launcher;

@SpirePatch(
        clz = ShopScreen.class,
        method = "updateRelics"
)
public class OnBuyRelicPatch {
    @SpireInsertPatch(
            rloc = 5,
            localvars = {
                "r"
            }
    )
    public static void Insert(ShopScreen __instance, StoreRelic r) {
        C204Launcher.replayRecorder.onBuyRelic(r);
    }
}
