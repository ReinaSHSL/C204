package reinashsl.ui;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import org.json.simple.JSONObject;
import reinashsl.C204Launcher;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ReplayButton {
    public static JSONObject loadedReplay;

    @SpireEnum
    static MenuButton.ClickResult REPLAYS;

    @SpirePatch(
            clz = MenuButton.class,
            method = "setLabel"
    )
    public static class SetLabel
    {
        public static void Postfix(MenuButton __instance)
        {
            try {
                if (__instance.result == REPLAYS) {
                    Field f_label = MenuButton.class.getDeclaredField("label");
                    f_label.setAccessible(true);
                    f_label.set(__instance, "Load Replay");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
            clz=MenuButton.class,
            method="buttonEffect"
    )
    public static class ButtonEffect
    {
        public static void Postfix(MenuButton __instance)
        {
            try {
                if (__instance.result == REPLAYS) {
                    JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        Field f_label = MenuButton.class.getDeclaredField("label");
                        f_label.setAccessible(true);
                        f_label.set(__instance, "Load Replay [" + file.getName() + "]");
                        C204Launcher.loadReplay(file);
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
            clz = MainMenuScreen.class,
            method = "setMainMenuButtons"
    )
    public static class MainMenuItem
    {
        @SpireInsertPatch(
                rloc=4,
                localvars={"index"}
        )
        public static void Insert(Object __obj_instance, @ByRef int[] index)
        {
            MainMenuScreen __instance = (MainMenuScreen)__obj_instance;
            __instance.buttons.add(new MenuButton(ReplayButton.REPLAYS, index[0]++));
        }
    }

}
