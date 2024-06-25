package service;

import entity.invoice.Invoice;

import java.util.List;

public interface InvoiceService {
    void createInvoice(Invoice invoice);

    Invoice findInvoiceByReservationId(Long reservationId);
    List<Invoice> findAllInvoices();
}
