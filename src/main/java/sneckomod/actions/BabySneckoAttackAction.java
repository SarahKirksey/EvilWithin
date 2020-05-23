package sneckomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import sneckomod.relics.BabySnecko;
import sneckomod.vfx.LoseGoldTextEffect;

public class BabySneckoAttackAction extends AbstractGameAction {
    AbstractMonster m;
    BabySnecko b;
    public BabySneckoAttackAction(AbstractMonster m, BabySnecko b) {
        this.m = m;
        this.b = b;
    }

    @Override
    public void update() {
        this.b.baby.state.setAnimation(0, "boop", false);
        this.b.baby.state.addAnimation(0, "idle", true, 0.0f);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        this.isDone = true;
    }
}