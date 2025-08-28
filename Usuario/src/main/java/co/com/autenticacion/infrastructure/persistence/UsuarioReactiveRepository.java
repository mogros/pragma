package co.com.autenticacion.infrastructure.persistence;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UsuarioReactiveRepository extends ReactiveCrudRepository<UsuarioEntity, UUID> {

    @Query("SELECT CASE WHEN COUNT(1)>0 THEN TRUE ELSE FALSE END FROM usuarios WHERE LOWER(correo_electronico)=LOWER($1)")
    Mono<Boolean> existsByCorreo(String correo);
}
