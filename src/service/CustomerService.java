package service;

import entity.reservation.Reservation;
import entity.reservation.Service;
import entity.room.enums.SpecialFeature;

import java.util.List;

public interface CustomerService {
    void createReservation(Reservation reservation);
    void addServices(Long reservationId, List<Service> services);
    void addSpecialFeatures(Long reservationId, List<SpecialFeature> specialFeatures);

}
