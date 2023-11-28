package Godwin.taxSolution.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String avatar;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<TaxPersonnel> taxPersonnel;

}
