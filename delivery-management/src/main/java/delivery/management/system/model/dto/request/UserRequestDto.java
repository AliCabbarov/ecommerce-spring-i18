package delivery.management.system.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Validated
public class UserRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Past
    private LocalDate birthdate;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @NumberFormat
    private Long phoneNumber;

}
