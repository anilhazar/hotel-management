package repository.impl;

import common.Person;
import common.Role;
import entity.customer.Customer;
import database.ConnectionManager;
import entity.manager.Manager;
import entity.owner.Owner;
import repository.PersonRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PersonRepositoryImpl implements PersonRepository {


    @Override
    public void save(Person person) {
        String query = "INSERT INTO person (name, email, age, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setInt(3, person.getAge());
            pstmt.setString(4, person.getRole().name());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Person person) {
        String query = "UPDATE person SET name = ?, email = ?, age = ?, role = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setInt(3, person.getAge());
            pstmt.setString(4, person.getRole().name());
            pstmt.setLong(5, person.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Person> findById(Long id) {
        String query = "SELECT * FROM person WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(factoryPerson(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT * FROM person";
        try (Connection connection = ConnectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                persons.add(factoryPerson(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    private Person factoryPerson(ResultSet rs) throws SQLException {
        Role role = Role.valueOf(rs.getString("role"));
        switch (role) {
            case OWNER:
                return new Owner(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        role
                );
            case CUSTOMER:
                return new Customer(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        role
                );
            case MANAGER:
                return new Manager(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        role
                );
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
