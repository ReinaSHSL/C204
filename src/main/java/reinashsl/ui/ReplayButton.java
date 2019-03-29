package reinashsl.ui;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;

import javax.swing.*;
import java.lang.reflect.Field;

public class ReplayButton {

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
            if (__instance.result == REPLAYS) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);
            }
        }
    }

}
