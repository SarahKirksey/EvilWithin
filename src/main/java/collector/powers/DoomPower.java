package collector.powers;

import collector.CollectorCollection;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.util.Wiz.atb;

public class DoomPower extends AbstractCollectorPower implements HealthBarRenderPower {
    public static final String NAME = "Doom";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.DEBUFF;
    public static final boolean TURN_BASED = false;

    public DoomPower(AbstractMonster target, int amount) {
        super(NAME, TYPE, TURN_BASED, target, null, amount);
    }

    @Override
    public void onInitialApplication() {
        checkInstakill();
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        checkInstakill();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        checkInstakill();
    }

    public void checkInstakill() {
        if (this.owner.currentHealth <= this.amount && owner.currentHealth > 0) {
            flash();
            atb(new HideHealthBarAction(owner));
            atb(new InstantKillAction(owner));
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    CollectorCollection.collect((AbstractMonster) owner);
                }
            });
        }
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return Color.PURPLE.cpy();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + FontHelper.colorString(owner.name, "y") + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}