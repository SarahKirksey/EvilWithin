package charbosses.cards.crowbot;

import basemod.abstracts.CustomCard;
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

public class CompressionMold extends CustomCard {
    public static final String ID = "downfall_Charboss:CompressionMold";
    private static final CardStrings cardStrings;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }

    public CompressionMold() {
        super(ID, CompressionMold.cardStrings.NAME, "downfallResources/images/cards/crowbot/compressionMold.png", 2, CompressionMold.cardStrings.DESCRIPTION, CardType.POWER,CharBossCrowbot.Enums.Crowbot_COLOR, CardRarity.RARE, CardTarget.SELF);

    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CompressionMold();
    }
}
