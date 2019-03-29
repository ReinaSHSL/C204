package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import org.json.simple.JSONObject;

public class ReplayRecorder implements
        PostPotionUseSubscriber,
        PostEnergyRechargeSubscriber,
        OnCardUseSubscriber,
        PostDeathSubscriber,
        StartGameSubscriber
{
    private JSONObject currentRunReplay = new JSONObject();

    public ReplayRecorder() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveCardUsed(AbstractCard c) {

    }

    @Override
    public void receivePostDeath() {

    }

    @Override
    public void receivePostEnergyRecharge() {

    }

    @Override
    public void receivePostPotionUse(AbstractPotion po) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void receiveStartGame() {
        currentRunReplay = new JSONObject();
        currentRunReplay.put("SEED", Settings.seed.toString());
        currentRunReplay.put("CHARACTER", AbstractDungeon.player.chosenClass.toString());
    }
}
