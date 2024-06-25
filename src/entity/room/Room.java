package entity.room;

import entity.reservation.Service;
import entity.room.enums.CommonFeature;
import entity.room.enums.Status;
import entity.room.enums.Type;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Long id;
    private int capacity;
    private final int basePrice;
    private Status status;
    private Type type;
    private List<Service> services;
    private final List<CommonFeature> commonFeatures = CommonFeature.COMMON_FEATURES;


    public Room(int capacity, Type type) {
        this.capacity = capacity;
        this.basePrice = CommonFeature.calculateTotalPrice(commonFeatures);
        status = Status.EMPTY;
        this.type = type;
        services = new ArrayList<>();

    }

    public Room(Long id, int capacity, Status status, Type type) {
        this.id = id;
        this.capacity = capacity;
        this.basePrice = CommonFeature.calculateTotalPrice(commonFeatures);
        this.status = status;
        this.type = type;
    }

    public Room(int capacity) {
        this.capacity = capacity;
        this.basePrice = CommonFeature.calculateTotalPrice(commonFeatures);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBasePrice() {
        return basePrice;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
