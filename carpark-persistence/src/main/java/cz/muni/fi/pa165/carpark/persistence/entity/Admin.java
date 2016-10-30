package cz.muni.fi.pa165.carpark.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User implements Serializable {

    public Admin() {
        super();
    }
}
