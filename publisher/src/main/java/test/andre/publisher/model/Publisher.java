package test.andre.publisher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
//@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    private Long id;

    @Column(name = "publisher_name")
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    protected Publisher() {    
    }

    public Publisher(String name) {
        this.name = name;
    }
}