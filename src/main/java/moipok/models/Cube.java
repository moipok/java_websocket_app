package moipok.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cube {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String color;

    public Cube() {
    }

    public Cube(String color) {
        this.color = color;
    }

    public Cube(Long id, String color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public String toString() {
        return id + color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
