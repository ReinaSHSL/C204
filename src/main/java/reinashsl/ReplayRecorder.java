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

    public ReplayRecorder() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveCardUsed(AbstractCard c) {

    }

    @Override
    public void receivePostDeath() {
        String path = FileSystems.getDefault().getPath(Gdx.files.getLocalStoragePath() + "replays" + File.separator + Settings.seed.toString() + ".shsl").toString();
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
        Floor floor = new Floor();
        floor.put("FLOOR_NUMBER", AbstractDungeon.floorNum);
        floorInfo.add(floor);
        currentRunReplay.put("FLOORS", floorInfo);
    }
}
