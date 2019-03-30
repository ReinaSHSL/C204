package reinashsl.interfaces;

import basemod.interfaces.ISubscriber;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public interface IEnterRoom {

    void receiveRoomEntry(AbstractRoom r);
}
