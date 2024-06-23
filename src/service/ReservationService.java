package service;

import entity.reservation.Reservation;
import entity.reservation.Service;
import entity.room.enums.SpecialFeature;

import java.util.List;

public interface ReservationService {
    void createReservation(Reservation reservation);
    Reservation findReservationById(Long reservationId);
    List<Reservation> findAllReservations();
    void updateReservation(Reservation reservation);

    Double calculateTotalPrice(Long reservationId);
}
