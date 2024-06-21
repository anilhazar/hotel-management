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
}
