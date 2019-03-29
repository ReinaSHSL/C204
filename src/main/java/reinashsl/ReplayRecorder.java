package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.PostDeathSubscriber;
import basemod.interfaces.PostEnergyRechargeSubscriber;
import basemod.interfaces.PostPotionUseSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class ReplayRecorder implements
        PostPotionUseSubscriber,
        PostEnergyRechargeSubscriber,
        OnCardUseSubscriber,
        PostDeathSubscriber
{

    public ReplayRecorder() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostDeath() {

    }

    @Override
    public void receivePostEnergyRecharge() {

    }

    @Override
    public void receivePostPotionUse(AbstractPotion abstractPotion) {

    }
}
