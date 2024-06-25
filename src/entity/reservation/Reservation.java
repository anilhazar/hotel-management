package entity.reservation;

import java.sql.Date;
import java.util.List;

public class Reservation {
    private Long id;
    private Long customerId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Double extendedPrice;
    private List<Service> services;

    public Reservation(Long customerId, Date checkInDate, Date checkOutDate, double extendedPrice) {
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.extendedPrice = extendedPrice;
    }

    public Reservation(Long roomId, String checkInDate, String checkOutDate) {
        this.roomId = roomId;
        this.checkInDate = Date.valueOf(checkInDate);
        this.checkOutDate = Date.valueOf(checkOutDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) { this.extendedPrice = extendedPrice; }

    public List<Service> getServices() { return services; }

    public void setServices(List<Service> services) { this.services = services; }

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
