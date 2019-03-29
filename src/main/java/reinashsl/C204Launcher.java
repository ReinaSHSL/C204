package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@SpireInitializer
public class C204Launcher implements
        PostInitializeSubscriber {
    public static Replay loadedReplay;

    public static void initialize() {
        BaseMod.subscribe(new C204Launcher());
    }

    @Override
    public void receivePostInitialize() {

    }

    public static void loadReplay(File replay) {
        Gson gson = new Gson();
        String path = replay.getAbsolutePath().replaceAll(".+:", "");
        String json = Gdx.files.absolute(path).readString(String.valueOf(StandardCharsets.UTF_8));
        loadedReplay = gson.fromJson(json, Replay.class);
        Settings.seed = Long.parseLong(loadedReplay.SEED);
        CardCrawlGame.chosenCharacter = AbstractPlayer.PlayerClass.valueOf(loadedReplay.CHARACTER);
        CardCrawlGame.mode = CardCrawlGame.GameMode.CHAR_SELECT;
        AbstractDungeon.generateSeeds();
        CardCrawlGame.mainMenuScreen.fadedOut = true;
        System.out.println(loadedReplay.SEED);
        System.out.println(loadedReplay.CHARACTER);
        System.out.println(Arrays.toString(loadedReplay.FLOOR_CHOICES));
        System.out.println(loadedReplay.FLOORS);
        System.out.println(loadedReplay.NEOW);
    }
}
