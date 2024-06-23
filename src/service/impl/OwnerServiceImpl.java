package service.impl;

import common.Person;
import entity.customer.Customer;
import entity.invoice.Invoice;
import entity.reservation.Reservation;
import repository.InvoiceRepository;
import repository.PersonRepository;
import repository.ReservationRepository;
import service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;
    private final InvoiceRepository invoiceRepository;

    public OwnerServiceImpl(ReservationRepository reservationRepository, PersonRepository personRepository, InvoiceRepository invoiceRepository) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Reservation> listAllReservations() {
        try {
            return reservationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to list all reservations", e);
        }
    }

    @Override
    public List<Invoice> listAllInvoices() {
        try {
            return invoiceRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to list all invoices", e);
        }
    }

    @Override
    public List<Person> listAllCustomers() {
        try {
            return personRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to list all customers", e);
        }
    }


}
