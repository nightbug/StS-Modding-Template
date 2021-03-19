package TemplateMod.vars;

import TemplateMod.cards.abs.abstractCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import static TemplateMod.TemplateMod.makeID;


public class cardMagicNumber extends DynamicVariable {

    @Override
    public String key() { return makeID("M"); }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof abstractCard) {
            return ((abstractCard) card).isSecondMagicNumberModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof abstractCard) {
            return ((abstractCard) card).SecondMagicNumber;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof abstractCard) {
            ((abstractCard) card).isSecondMagicNumberModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof abstractCard) {
            return ((abstractCard) card).BaseSecondMagicNumber;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof abstractCard) {
            return ((abstractCard) card).upgradedSecondMagicNumber;
        }
        return false;
    }
}