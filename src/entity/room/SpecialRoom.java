package entity.room;

import entity.room.enums.Type;

import java.util.List;

public class SpecialRoom extends Room {
    private List<SpecialFeature> specialFeatures;

    public SpecialRoom(Long id, int capacity, Type type) {
        super(capacity, type);
    }

    public SpecialRoom(int capacity, List<SpecialFeature> specialFeatures) {
        super(capacity);
        this.specialFeatures = specialFeatures;
    }

    public List<SpecialFeature> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(List<SpecialFeature> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public void addSpecialFeature(String name, int price) {
        specialFeatures.add(new SpecialFeature(name, price));
    }

}
