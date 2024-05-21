package kodlamaio.nortwind.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="email",unique = true)
    @Email
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "username")
    @NotNull
    @NotBlank
    private String userName;

    @Column(name="password")
    @NotBlank
    @NotNull
    private String password;


}
