package evilWithin.events;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.shrines.Lab;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import evilWithin.EvilWithinMod;
import evilWithin.potions.CursedFountainPotion;

import java.util.ArrayList;
import java.util.List;

public class CursedFountain extends AbstractImageEvent {
    public static final String ID = "evilWithin:CursedFountain";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private int screenNum = 0;
    private int curseCount = 0;
    private int goldAmt;



    public CursedFountain() {
        super(NAME, DESCRIPTIONS[0], (EvilWithinMod.assetPath("images/events/cursedFountain.png")));
        this.noCardsInRewards = true;
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.goldAmt = 25;
        } else {
            this.goldAmt = 75;
        }
    }

    public void onEnterRoom() {
        CardCrawlGame.music.playTempBGM("SHRINE");
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_FOUNTAIN",-0.2F);
        }
        for(int i = AbstractDungeon.player.masterDeck.group.size() - 1; i >= 0; --i) {
            if ((AbstractDungeon.player.masterDeck.group.get(i)).type == CardType.CURSE) {
                curseCount++;
            }
        }
        if (curseCount >=1){
            this.imageEventText.setDialogOption(OPTIONS[0]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[3], true);
        }
        if (curseCount >=2){
            this.imageEventText.setDialogOption(OPTIONS[1]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[4], true);
        }
        if (curseCount >=3){
            this.imageEventText.setDialogOption(OPTIONS[2]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[5], true);
        }
        this.imageEventText.setDialogOption(OPTIONS[7]);

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[6],true);
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new CursedFountainPotion()));
                        AbstractDungeon.combatRewardScreen.open();
                        return;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(1, OPTIONS[6],true);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldAmt));
                        AbstractDungeon.player.gainGold(this.goldAmt);
                        return;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(2, OPTIONS[6],true);
                        AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
                        return;
                    case 3:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[7]);
                        this.screenNum = 1;
                        return;
                }
            default:
                this.openMap();
        }

    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
    }
}
