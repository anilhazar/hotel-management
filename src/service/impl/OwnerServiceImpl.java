package service.impl;

import entity.invoice.Invoice;
import entity.person.Person;
import entity.reservation.Reservation;
import repository.InvoiceRepository;
import repository.PersonRepository;
import repository.ReservationRepository;
import repository.impl.InvoiceRepositoryImpl;
import repository.impl.PersonRepositoryImpl;
import repository.impl.ReservationRepositoryImpl;
import service.OwnerService;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;
    private final InvoiceRepository invoiceRepository;

    public OwnerServiceImpl() {
        this.reservationRepository = new ReservationRepositoryImpl();
        this.personRepository = new PersonRepositoryImpl();
        this.invoiceRepository = new InvoiceRepositoryImpl();
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

        List<Person> customers = personRepository.findAll();

        if (customers == null) {
            throw new RuntimeException("No customer found");
        }
        return customers;
    }


}
