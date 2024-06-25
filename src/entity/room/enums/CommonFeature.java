package entity.room.enums;

import java.util.List;

public enum CommonFeature {
    TV("TV", 100),
    BATHROOM("BATHROOM", 100),
    TOWEL("TOWEL", 50),
    BED("BED", 200),
    SOFA("SOFA", 100),

    TABLE("TABLE", 100);

    private final String name;
    private final int price;

    CommonFeature(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static final List<CommonFeature> COMMON_FEATURES = List.of(CommonFeature.values());

    public static int calculateTotalPrice(List<CommonFeature> features) {
        int totalPrice = 0;
        for (CommonFeature feature : features) {
            totalPrice += feature.getPrice();
        }
        return totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
