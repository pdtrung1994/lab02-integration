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
    private String id;
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

    public String getId() {
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
        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? lastName.equals(person.lastName) : person.lastName == null) return false;
        if (homeLongitude != null ? homeLongitude.equals(person.homeLongitude) : person.homeLongitude == null) return false;
        return homeLatitude != null ? homeLatitude.equals(person.homeLatitude) : person.homeLatitude == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (homeLongitude != null ? homeLongitude.hashCode() : 0);
        result = 31 * result + (homeLatitude != null ? homeLatitude.hashCode() : 0);
        return result;
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
