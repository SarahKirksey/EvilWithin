package collector.cards.collectibles;

import collector.powers.collectioncards.GremlinWizardCardPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.applyToSelf;

public class GremlinWizardCard extends AbstractCollectibleCard {
    public final static String ID = makeID(GremlinWizardCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 10, 5

    public GremlinWizardCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GremlinWizardCardPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}