package Godwin.taxSolution.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tax_officer")
public class TaxOfficer {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private int telephone;
    private String address;
    private String cityName;
    private int pIva;
    private String description;
    private String image;

    @CreationTimestamp
    private Date createdAt;

    @ManyToOne()
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
