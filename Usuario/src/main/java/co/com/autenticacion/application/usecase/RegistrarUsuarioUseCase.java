package co.com.autenticacion.application.usecase;

import co.com.autenticacion.domain.model.Usuario;
import co.com.autenticacion.domain.port.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class RegistrarUsuarioUseCase {

    private final UsuarioRepositoryPort repository;

    @Transactional
    public Mono<Usuario> ejecutar(Usuario usuario) {
        log.debug("Validando usuario: {}", usuario.getCorreoElectronico());
        // validations already in domain, but double-check business rules
        return repository.existsByCorreo(usuario.getCorreoElectronico())
                .flatMap(ex -> {
                    if (Boolean.TRUE.equals(ex)) {
                        return Mono.error(new IllegalStateException("El correo_electronico ya está registrado"));
                    }
                    return repository.save(usuario);
                })
                .doOnSuccess(u -> log.info("Usuario registrado: {}", u.getId()));
    }
}
