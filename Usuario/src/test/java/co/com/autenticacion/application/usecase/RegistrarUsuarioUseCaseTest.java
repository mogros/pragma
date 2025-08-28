package co.com.autenticacion.application.usecase;

import co.com.autenticacion.domain.model.Usuario;
import co.com.autenticacion.domain.port.UsuarioRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;

class RegistrarUsuarioUseCaseTest {

    private UsuarioRepositoryPort repo;
    private RegistrarUsuarioUseCase useCase;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(UsuarioRepositoryPort.class);
        useCase = new RegistrarUsuarioUseCase(repo);
    }

    @Test
    void registrar_ok() {
        Usuario u = Usuario.builder()
                .id(UUID.randomUUID())
                .nombres("Ana")
                .apellidos("Pérez")
                .fechaNacimiento(LocalDate.of(1992,5,2))
                .correoElectronico("ana@example.com")
                .salarioBase(new BigDecimal("2500.50"))
                .build();

        when(repo.existsByCorreo("ana@example.com")).thenReturn(Mono.just(false));
        when(repo.save(u)).thenReturn(Mono.just(u));

        StepVerifier.create(useCase.ejecutar(u))
                .expectNext(u)
                .verifyComplete();

        verify(repo).save(u);
    }

    @Test
    void registrar_correo_duplicado() {
        Usuario u = Usuario.builder()
                .nombres("Ana")
                .apellidos("Pérez")
                .correoElectronico("ana@example.com")
                .salarioBase(new BigDecimal("1000"))
                .build();

        when(repo.existsByCorreo("ana@example.com")).thenReturn(Mono.just(true));

        StepVerifier.create(useCase.ejecutar(u))
                .expectError(IllegalStateException.class)
                .verify();
    }
}
