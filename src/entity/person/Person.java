package entity.person;

import entity.person.enums.Role;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private Long id;
    private String name;
    private String email;
    private int age;
    private Role role;
    private String password;
    private byte[] salt;

    public Person(String name, String password, String email, int age, Role role, byte[] salt) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
        this.password = password;
        this.salt = salt;
    }

    public Person(String name, String password, String email, int age, Role role) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(email, person.email) &&
                role == person.role &&
                Objects.equals(password, person.password) &&
                Arrays.equals(salt, person.salt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, age, role, password);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
