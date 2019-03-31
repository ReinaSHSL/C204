package reinashsl.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.shop.StorePotion;
import com.megacrit.cardcrawl.shop.StoreRelic;
import reinashsl.C204Launcher;

@SpirePatch(
        clz = ShopScreen.class,
        method = "updatePotions"
)
public class OnBuyPotionPatch {
    @SpireInsertPatch(
            rloc = 4,
            localvars = {
                    "p"
            }
    )
    public static void Insert(ShopScreen __instance, StorePotion p) {
        C204Launcher.replayRecorder.onBuyPotion(p);
    }
}
