package repository;

import entity.invoice.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {
    void save(Invoice invoice);
    Optional<Invoice> findById(Long id);
    Optional<Invoice> findByReservationId(Long reservationId);
    List<Invoice> findAll();
    void update(Invoice invoice);

}
