package room;

import room.enums.SpecialFeature;

import java.util.List;

public class SpecialRoom extends Room {
    private List<SpecialFeature> specialFeatures;

    public SpecialRoom(int capacity, int basePrice) {
        super(capacity, basePrice);
    }
}
