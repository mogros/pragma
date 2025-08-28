package co.com.autenticacion.domain.model;

import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private UUID id;

    @NotBlank(message = "nombres es requerido")
    private String nombres;

    @NotBlank(message = "apellidos es requerido")
    private String apellidos;

    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;

    @NotBlank(message = "correo_electronico es requerido")
    @Email(message = "correo_electronico no tiene un formato válido")
    private String correoElectronico;

    @NotNull(message = "salario_base es requerido")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "15000000.0", inclusive = true)
    private BigDecimal salarioBase;
}
