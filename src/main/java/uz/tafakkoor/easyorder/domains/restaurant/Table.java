package uz.tafakkoor.easyorder.domains.restaurant;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Image;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@jakarta.persistence.Table(name = "tables")

public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Integer capacity;
    private boolean isBooked;

    private boolean isDeleted;
    @OneToOne
    private Image qrCode;
    @ManyToOne
    private Restaurant restaurant;
}
