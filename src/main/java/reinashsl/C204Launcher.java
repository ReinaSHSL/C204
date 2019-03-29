package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.core.util.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void loadReplay(File replay) throws IOException {
        Gson gson = new Gson();
        String path = replay.getAbsolutePath().replaceAll(".+:", "");
        String json = Gdx.files.absolute(path).readString(String.valueOf(StandardCharsets.UTF_8));
        loadedReplay = gson.fromJson(json, Replay.class);
        System.out.println(loadedReplay.SEED);
        System.out.println(loadedReplay.CHARACTER);
        System.out.println(Arrays.toString(loadedReplay.FLOOR_CHOICES));
        System.out.println(loadedReplay.FLOORS);
        System.out.println(loadedReplay.NEOW);
    }
}
