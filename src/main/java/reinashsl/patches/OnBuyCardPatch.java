package reinashsl.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.shop.ShopScreen;
import reinashsl.C204Launcher;

@SpirePatch(
        clz = ShopScreen.class,
        method = "update"
)
public class OnBuyCardPatch {
    @SpireInsertPatch(
            rloc = 62,
            localvars = {
                    "hoveredCard"
    }
    )
    public static void Insert(ShopScreen __instance, AbstractCard hoveredCard) {
        C204Launcher.replayRecorder.onBuyCard(hoveredCard);
    }
}
