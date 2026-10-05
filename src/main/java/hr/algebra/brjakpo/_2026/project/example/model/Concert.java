package hr.algebra.brjakpo._2026.project.example.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Concert {
    private String name;
    private LocalDateTime startDateTime;
    private String venue;
}
