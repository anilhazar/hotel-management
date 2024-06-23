package service;

import entity.invoice.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    void createInvoice(Invoice invoice);
    Optional<Invoice> findInvoiceByReservationId(Long reservationId);
    List<Invoice> findAllInvoices();
}
