package co.com.autenticacion.domain.port;

import co.com.autenticacion.domain.model.Usuario;
import reactor.core.publisher.Mono;

public interface UsuarioRepositoryPort {
    Mono<Boolean> existsByCorreo(String correo);
    Mono<Usuario> save(Usuario usuario);
}
