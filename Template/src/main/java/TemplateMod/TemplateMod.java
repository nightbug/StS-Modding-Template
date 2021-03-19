package TemplateMod;

import TemplateMod.cards.abs.abstractCard;
import TemplateMod.relics.AbstractRelic;
import TemplateMod.relics.CharacterStarterRelic;
import TemplateMod.vars.cardMagicNumber;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import TemplateMod.character.TemplateChar;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class TemplateMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber {

    public static final String modID = "templateMod";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public TemplateMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TemplateChar.Enums.templateColor, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makeOutlinePath(String resourcePath) {
        return modID + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        TemplateMod thismod = new TemplateMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TemplateChar(TemplateChar.characterStrings.NAMES[1], TemplateChar.Enums.templateEnum),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TemplateChar.Enums.templateEnum);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new CharacterStarterRelic(), TemplateChar.Enums.templateColor);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new cardMagicNumber());
        new AutoAdd(modID)
                .packageFilter(abstractCard.class)
                .setDefaultSeen(true)
                .cards();


    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
