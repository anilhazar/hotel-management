package common;

public abstract class Person {
    private Long id;
    protected String name;
    protected String email;
    protected int age;
    protected Role role;

    protected Person(String name, String email, int age, Role role) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
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

    public int getAge() {
        return age;
    }


    public Role getRole() {
        return role;
    }


}
