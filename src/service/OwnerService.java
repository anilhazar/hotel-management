package service;

import entity.invoice.Invoice;
import entity.person.Person;
import entity.reservation.Reservation;

import java.util.List;

public interface OwnerService {
    List<Reservation> listAllReservations();
    List<Person> listAllCustomers();
    List<Invoice> listAllInvoices();
}
