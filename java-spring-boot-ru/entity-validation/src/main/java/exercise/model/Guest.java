package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;




@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    // BEGIN
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    @Pattern(regexp = "^\\+[0-9]{11,13}$",
             message = "Phone number must start with + and contain 11-13 digits")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Pattern(regexp = "^\\d{4}$",
             message = "Club card must be exactly 4 digits")
    @Column(name = "club_card", nullable = false)
    private String clubCard;

    @Future(message = "Card expiration date must be in the future")
    @Column(name = "card_valid_until", nullable = false)
    private LocalDate cardValidUntil;
    // END

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;
}
