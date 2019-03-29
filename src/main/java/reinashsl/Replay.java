package reinashsl;

public class Replay {
    String SEED;
    String CHARACTER;
    Floor[] FLOORS;
    String[] FLOOR_CHOICES;
    String NEOW;

    public class Floor {
        int FLOOR_NUMBER;
        String[] ACTIONS;
    }
}
