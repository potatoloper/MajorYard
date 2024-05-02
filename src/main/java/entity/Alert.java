package entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Alert")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // alert_no

}
