package example.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;

    private String homeLongitude;
    private String homeLatitude;

    protected Person() {}

    public Person(String firstName, String lastName, String homeLongitude, String homeLatitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeLongitude = homeLongitude;
        this.homeLatitude = homeLatitude;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeLongitude() { return homeLongitude; }

    public String getHomeLatitude() { return homeLatitude; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(homeLongitude, person.homeLongitude) &&
                Objects.equals(homeLatitude, person.homeLatitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, homeLongitude, homeLatitude);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", homeLongitude='" + homeLongitude + '\'' +
                ", homeLatitude='" + homeLatitude + '\'' +
                '}';
    }
}
