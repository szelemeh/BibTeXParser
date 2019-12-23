package model;

import java.util.Objects;

public class Name {
    private String firstName = null;
    private String lastName = null;
    private String von = null;
    private String jr = null;

    public Name() {

    }

    public Name(String firstName, String lastName, String von, String jr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.von = von;
        this.jr = jr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(!firstName.isEmpty())this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(!lastName.isEmpty()) this.lastName = lastName;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        if(!von.isEmpty())this.von = von;
    }

    public String getJr() {
        return jr;
    }

    public void setJr(String jr) {
        if(!jr.isEmpty()) this.jr = jr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) &&
                Objects.equals(lastName, name.lastName) &&
                Objects.equals(von, name.von) &&
                Objects.equals(jr, name.jr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, von, jr);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", von='" + von + '\'' +
                ", jr='" + jr + '\'' +
                '}';
    }
}
