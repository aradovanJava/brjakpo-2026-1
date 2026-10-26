package hr.algebra.brjakpo._2026.project.example.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConcertForm {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 80, message = "Keep the name under 80 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Pick a start date and time")
    @Future(message = "The start date and time of the concert must be in the future")
    private LocalDateTime startDateTime;

    @NotBlank(message = "Venue is required")
    @Size(max = 80, message = "Keep the venue name under 80 characters")
    private String venue;
}
