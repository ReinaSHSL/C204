package reinashsl.interfaces;

import basemod.interfaces.ISubscriber;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public interface IEnterRoom {

    void receiveRoomEntry(MapRoomNode r);
}
