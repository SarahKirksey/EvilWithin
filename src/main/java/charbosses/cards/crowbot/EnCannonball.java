package charbosses.cards.crowbot;

import charbosses.bosses.Crowbot.CharBossCrowbot;
import charbosses.cards.AbstractBossCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.downfallMod;

public class EnCannonball extends AbstractBossCard {
    public static final String ID = "downfall_Charboss:Cannonball";
    private static final CardStrings cardStrings;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }

    public EnCannonball() {
        super(ID, EnCannonball.cardStrings.NAME, "crowbot/cannonball", 3, EnCannonball.cardStrings.DESCRIPTION, CardType.ATTACK,CardColor.BLUE, CardRarity.RARE, CardTarget.ENEMY, AbstractMonster.Intent.ATTACK,true);
        this.baseDamage = 20;
        this.tags.add(CharBossCrowbot.Enums.AMMO);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnCannonball();
    }
}
