package co.com.autenticacion.infrastructure.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("usuarios")
public class UsuarioEntity {
    @Id
    private UUID id;

    private String nombres;
    private String apellidos;

    @Column("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String direccion;
    private String telefono;

    @Column("correo_electronico")
    private String correoElectronico;

    @Column("salario_base")
    private BigDecimal salarioBase;

    @Column("creado_en")
    private OffsetDateTime creadoEn;

    @Column("actualizado_en")
    private OffsetDateTime actualizadoEn;
}
