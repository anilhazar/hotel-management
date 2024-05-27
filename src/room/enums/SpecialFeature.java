package room.enums;

public enum SpecialFeature {
    JACUZZI("JACUZZI", 300),
    SAUNA("SAUNA", 250),
    MINIBAR("MINIBAR", 150),
    BALCONY("BALCONY", 200),
    FIREPLACE("FIREPLACE", 200);

    private final String name;
    private final int price;

    SpecialFeature(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
