package co.com.autenticacion.infrastructure.web.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotBlank(message = "nombres es requerido")
    private String nombres;

    @NotBlank(message = "apellidos es requerido")
    private String apellidos;

    private LocalDate fecha_nacimiento;
    private String direccion;
    private String telefono;

    @NotBlank(message = "correo_electronico es requerido")
    @Email(message = "correo_electronico no tiene un formato válido")
    private String correo_electronico;

    @NotNull(message = "salario_base es requerido")
    @DecimalMin(value = "0.0", inclusive = true, message = "salario_base debe ser >= 0")
    @DecimalMax(value = "15000000.0", inclusive = true, message = "salario_base debe ser <= 15000000")
    private BigDecimal salario_base;
}
