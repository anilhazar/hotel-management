package room;

import customer.Service;
import room.enums.CommonFeature;
import room.enums.Status;
import room.enums.Type;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private int capacity;
    private int basePrice;
    private Status status;
    private Type type;
    private List<Service> services;
    private final List<CommonFeature> commonFeatures = CommonFeature.COMMON_FEATURES;


    public Room(int capacity, int basePrice, Type type) {
        this.capacity = capacity;
        this.basePrice = CommonFeature.calculateTotalPrice(commonFeatures);
        status = Status.EMPTY;
        this.type = type;
        services = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public List<CommonFeature> getCommonFeatures() {
        return commonFeatures;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}
