package co.com.autenticacion.infrastructure.persistence;

import co.com.autenticacion.domain.model.Usuario;
import co.com.autenticacion.domain.port.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioReactiveRepository repository;

    @Override
    public Mono<Boolean> existsByCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        UsuarioEntity entity = UsuarioEntity.builder()
                .id(usuario.getId())
                .nombres(usuario.getNombres())
                .apellidos(usuario.getApellidos())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .direccion(usuario.getDireccion())
                .telefono(usuario.getTelefono())
                .correoElectronico(usuario.getCorreoElectronico())
                .salarioBase(usuario.getSalarioBase())
                .build();
        return repository.save(entity).map(e -> Usuario.builder()
                .id(e.getId())
                .nombres(e.getNombres())
                .apellidos(e.getApellidos())
                .fechaNacimiento(e.getFechaNacimiento())
                .direccion(e.getDireccion())
                .telefono(e.getTelefono())
                .correoElectronico(e.getCorreoElectronico())
                .salarioBase(e.getSalarioBase())
                .build());
    }
}
