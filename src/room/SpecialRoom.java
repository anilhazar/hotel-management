package room;

import room.enums.SpecialFeature;
import room.enums.Type;

import java.util.List;

public class SpecialRoom extends Room {
    private List<SpecialFeature> specialFeatures;

    public SpecialRoom(Long id, int capacity, int basePrice, Type type) {
        super(capacity, basePrice, type);
    }



    public List<SpecialFeature> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(List<SpecialFeature> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public void addSpecialFeatureByName(String featureName) {
        specialFeatures.add(SpecialFeature.valueOf(featureName.toUpperCase()));
    }

}
