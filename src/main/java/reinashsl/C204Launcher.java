package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.JsonArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpireInitializer
public class C204Launcher implements
        PostInitializeSubscriber {

    public static void initialize() {
        BaseMod.subscribe(new C204Launcher());
    }

    @Override
    public void receivePostInitialize() {

    }

    public static void loadReplay(File replay) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(replay.getAbsoluteFile());
            JsonArray a = (JsonArray) jsonParser.parse(reader);
            for (Object o : a) {
                System.out.println(o);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
