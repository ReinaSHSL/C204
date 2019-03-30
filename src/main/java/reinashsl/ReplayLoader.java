package reinashsl;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ReplayLoader {


    public static void loadReplay(File replay) {
        try {
            Gson gson = new Gson();
            String path = replay.getAbsolutePath().replaceAll(".+:", "");
            String json = Gdx.files.absolute(path).readString(String.valueOf(StandardCharsets.UTF_8));
            C204Launcher.loadedReplay = gson.fromJson(json, Replay.class);
            Settings.seed = Long.parseLong(C204Launcher.loadedReplay.SEED);
            CardCrawlGame.chosenCharacter = AbstractPlayer.PlayerClass.valueOf(C204Launcher.loadedReplay.CHARACTER);
            CardCrawlGame.mode = CardCrawlGame.GameMode.CHAR_SELECT;
            AbstractDungeon.generateSeeds();
            CardCrawlGame.mainMenuScreen.fadedOut = true;
        } catch (Exception e) {
            System.out.println("pls pick a valid replay");
        }

    }

}
