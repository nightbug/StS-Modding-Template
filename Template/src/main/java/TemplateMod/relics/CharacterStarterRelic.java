package TemplateMod.relics;

import TemplateMod.util.RelicInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static TemplateMod.TemplateMod.makeID;

public class CharacterStarterRelic extends TemplateMod.relics.AbstractRelic {
    private static final RelicInfo relicInfo = new RelicInfo(
            CharacterStarterRelic.class.getSimpleName(),
            AbstractRelic.RelicTier.STARTER,
            AbstractRelic.LandingSound.MAGICAL
    );
    public static final String ID = makeID(relicInfo.relicName);
    public CharacterStarterRelic() {
        super(ID, relicInfo);
        fixDescription();
    }
    @Override
    public String getUpdatedDescription() { return DESCRIPTIONS[0]; }
}
