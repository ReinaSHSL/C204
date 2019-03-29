package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

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
