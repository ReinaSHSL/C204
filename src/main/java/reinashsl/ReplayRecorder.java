package reinashsl;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reinashsl.interfaces.IEnterRoom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class ReplayRecorder implements
        PostPotionUseSubscriber,
        PostEnergyRechargeSubscriber,
        OnCardUseSubscriber,
        PostDeathSubscriber,
        StartGameSubscriber,
        IEnterRoom
{
    private JSONObject currentRunReplay;
    private JSONArray floorInfo = new JSONArray();
    private JSONArray currentFloorActions = null;

    public ReplayRecorder() {
        BaseMod.subscribe(this);
    }

    @SuppressWarnings("unchecked")
    public void addToCurrentFloorActions(String action) {
        if (currentFloorActions != null) {
            currentFloorActions.add(action);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void receiveCardUsed(AbstractCard c) {
        if (currentFloorActions != null) {
            currentFloorActions.add("PLAYED " + c.cardID + " " + c.timesUpgraded + " " + c.misc);
        }
    }

    @Override
    public void receivePostDeath() {
        String path;
        File doesThisExist;
        do {
            int i = 0;
            path = FileSystems.getDefault().getPath(Gdx.files.getLocalStoragePath() + "replays" + File.separator + Settings.seed.toString() + i + ".shsl").toString();
            ++i;
            doesThisExist = new File(path);
        } while (doesThisExist.exists());
        try (FileWriter fw = new FileWriter(path)){
            fw.write(currentRunReplay.toJSONString());
            fw.close();
            System.out.println("Replay saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivePostEnergyRecharge() {

    }

    @Override
    public void receivePostPotionUse(AbstractPotion po) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void receiveStartGame() {
        currentRunReplay = new JSONObject();
        currentRunReplay.put("SEED", Settings.seed.toString());
        currentRunReplay.put("CHARACTER", AbstractDungeon.player.chosenClass.toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void receiveRoomEntry(MapRoomNode r) {
        if (currentFloorActions != null && !currentFloorActions.isEmpty()) {
            Floor floor = new Floor();
            floor.put("FLOOR_NUMBER", AbstractDungeon.floorNum);
            floor.ACTIONS = new String[currentFloorActions.size()];
            for (int i = 0; i < currentFloorActions.size(); i++) {
                floor.ACTIONS[i] = (String) currentFloorActions.get(i);
            }
            floor.put("ACTIONS", Arrays.toString(floor.ACTIONS));
            floorInfo.add(floor);
            currentRunReplay.put("FLOORS", floorInfo);
        }
        currentFloorActions = new JSONArray();
    }

}
