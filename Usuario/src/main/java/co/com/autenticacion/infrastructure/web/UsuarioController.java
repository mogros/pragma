package co.com.autenticacion.infrastructure.web;

import co.com.autenticacion.application.usecase.RegistrarUsuarioUseCase;
import co.com.autenticacion.domain.model.Usuario;
import co.com.autenticacion.infrastructure.web.dto.UsuarioRequest;
import co.com.autenticacion.infrastructure.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final RegistrarUsuarioUseCase useCase;

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UsuarioResponse> crear(@Valid @RequestBody UsuarioRequest request) {
        log.debug("Recibida solicitud de creación de usuario con correo {}", request.getCorreo_electronico());
        Usuario u = Usuario.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFecha_nacimiento())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .correoElectronico(request.getCorreo_electronico())
                .salarioBase(request.getSalario_base())
                .build();
        return useCase.ejecutar(u)
                .map(saved -> UsuarioResponse.builder()
                        .id(saved.getId())
                        .nombres(saved.getNombres())
                        .apellidos(saved.getApellidos())
                        .fecha_nacimiento(saved.getFechaNacimiento())
                        .direccion(saved.getDireccion())
                        .telefono(saved.getTelefono())
                        .correo_electronico(saved.getCorreoElectronico())
                        .salario_base(saved.getSalarioBase())
                        .build());
    }
}
