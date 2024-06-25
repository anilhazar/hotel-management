package service;

import entity.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    void createReservation(Reservation reservation);
    Reservation findReservationById(Long reservationId);
    List<Reservation> findAllReservations();
    void updateReservation(Reservation reservation);
    Double calculateTotalPrice(Long reservationId);
}
