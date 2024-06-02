package repository;

import reservation.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    void update(Reservation reservation);

    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    void save(Reservation reservation);
}

