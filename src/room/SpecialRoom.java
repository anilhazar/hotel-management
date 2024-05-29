package room;

import room.enums.SpecialFeature;
import room.enums.Type;

import java.util.List;

public class SpecialRoom extends Room {
    private List<SpecialFeature> specialFeatures;

    public SpecialRoom(int capacity, int basePrice, Type type) {
        super(capacity, basePrice, type);
    }
}
