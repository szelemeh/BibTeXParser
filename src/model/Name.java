package model;

import java.util.Objects;

/**
 * Name is a class that holds information about
 * different parts of name such as:
 * <ul>
 *     <li>first name</li>
 *     <li>last name</li>
 *     <li>von</li>
 *     <li>jr</li>
 * </ul>
 */
public class Name {
    private String firstName = null;
    private String lastName = null;
    private String von = null;
    private String jr = null;

    /**
     * Constructs an empty name.
     */
    public Name() {

    }

    /**
     * Constructs a full name.
     * @param firstName
     * @param lastName
     * @param von
     * @param jr
     */
    public Name(String firstName, String lastName, String von, String jr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.von = von;
        this.jr = jr;
    }

    /**
     * Gets firstName
     * @return <code>String</code>
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName.
     * @param firstName is a new firstName.
     */
    public void setFirstName(String firstName) {
        if(!firstName.isEmpty())this.firstName = firstName;
    }

    /**
     * Gets lastName
     * @return <code>String</code>
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName.
     * @param lastName is a new lastName.
     */
    public void setLastName(String lastName) {
        if(!lastName.isEmpty()) this.lastName = lastName;
    }

    /**
     * Gets von.
     * @return <code>String</code>
     */
    public String getVon() {
        return von;
    }

    /**
     * Sets von.
     * @param von is a new von.
     */
    public void setVon(String von) {
        if(!von.isEmpty())this.von = von;
    }

    /**
     * Gets jr.
     * @return <code>String</code>
     */
    public String getJr() {
        return jr;
    }

    /**
     * Sets jr.
     * @param jr is a new jr.
     */
    public void setJr(String jr) {
        if(!jr.isEmpty()) this.jr = jr;
    }

    /**
     * @see Object
     */
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

    /**
     * @see Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, von, jr);
    }

    /**
     * @see Object
     */
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
