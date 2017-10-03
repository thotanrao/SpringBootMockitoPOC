package com.thota.mockito.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Comparable<User> {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) { return false; }
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) { return false; }
        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }

	@Override
	public int compareTo(User user) {
		
		return this.getLastName().compareTo(user.getLastName());
	}
}
