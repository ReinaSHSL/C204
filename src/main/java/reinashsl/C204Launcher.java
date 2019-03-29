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
    public static ReplayRecorder replayRecorder;
    public static ReplayLoader replayLoader;

    public static void initialize() {
        BaseMod.subscribe(new C204Launcher());
    }

    @Override
    public void receivePostInitialize() {
        replayRecorder = new ReplayRecorder();
        replayLoader = new ReplayLoader();
    }

}
