package test.andre.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author {
    private String id;
    private String firstName;
    private String lastName;

    public Author() {}
    public Author(String id, String fname, String lname) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
    }

/**
 * @return the firstName
 */
public String getFirstName() {
    return firstName;
}

/**
 * @return the id
 */
public String getId() {
    return id;
}

/**
 * @return the lastName
 */
public String getLastName() {
    return lastName;
}

/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
    this.firstName = firstName;
}

/**
 * @param id the id to set
 */
public void setId(String id) {
    this.id = id;
}

/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
    this.lastName = lastName;
}

}