package automaton.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.util.CardIgnore;

@CardIgnore
public class EasyModalChoiceCard extends AbstractBronzeCard {

    private Runnable onUseOrChosen;
    private String passedName;
    private String passedDesc;

    public EasyModalChoiceCard(String ID, String name, String description, Runnable onUseOrChosen) {
        super(ID, -2, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractCard.CardColor.COLORLESS);
        this.name = this.originalName = passedName = name;
        this.rawDescription = passedDesc = description;
        this.onUseOrChosen = onUseOrChosen;
        initializeTitle();
        initializeDescription();
    }

    @Override
    public void onChoseThisOption() {
        onUseOrChosen.run();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onUseOrChosen.run();
    }

    @Override
    public void upp() {

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new EasyModalChoiceCard(cardID, passedName, passedDesc, onUseOrChosen);
    }
}
