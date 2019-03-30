package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import java.io.File;

@SpireInitializer
public class C204Launcher implements
        PostInitializeSubscriber {
    public static Replay loadedReplay;
    public static ReplayRecorder replayRecorder;
    public static ReplayLoader replayLoader;

    @SuppressWarnings("unused")
    public static void initialize() {
        BaseMod.subscribe(new C204Launcher());
    }

    @Override
    public void receivePostInitialize() {
        createReplayDirectory();
        replayRecorder = new ReplayRecorder();
        replayLoader = new ReplayLoader();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createReplayDirectory() {
        File dir = new File(Gdx.files.getLocalStoragePath() + "replays" + File.separator);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

}
