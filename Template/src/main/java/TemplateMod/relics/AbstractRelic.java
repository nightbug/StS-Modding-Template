package TemplateMod.relics;

import TemplateMod.util.RelicInfo;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.PowerTip;


public abstract class AbstractRelic extends CustomRelic {
    public AbstractRelic(String ID, RelicInfo relicInfo) { super(ID, relicInfo.loadRelicImage(relicInfo.relicName), relicInfo.loadOutlineImage(relicInfo.relicName), relicInfo.relicTier, relicInfo.sfx); }
    public void fixDescription() {
        description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
}