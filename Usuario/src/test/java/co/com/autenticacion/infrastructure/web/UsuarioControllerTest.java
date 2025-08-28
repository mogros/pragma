package co.com.autenticacion.infrastructure.web;

import co.com.autenticacion.application.usecase.RegistrarUsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsuarioControllerTest {

    private WebTestClient client;
    private RegistrarUsuarioUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = Mockito.mock(RegistrarUsuarioUseCase.class);
        UsuarioController controller = new UsuarioController(useCase);
        client = WebTestClient.bindToController(controller).build();
    }

    @Test
    void crear_ok() {
        when(useCase.ejecutar(any())).thenReturn(Mono.just(new co.com.autenticacion.domain.model.Usuario(null, "Ana", "Pérez", LocalDate.of(1992,5,2), "Av 123", "999", "ana@example.com", new BigDecimal("2500.50"))));

        //String json = "{"nombres":"Ana","apellidos":"Pérez","fecha_nacimiento":"1992-05-02","direccion":"Av 123","telefono":"999","correo_electronico":"ana@example.com","salario_base":2500.50}";
        String json = "{\"nombres\":\"Ana\",\"apellidos\":\"Pérez\",\"fecha_nacimiento\":\"1992-05-02\",\"direccion\":\"Av 123\",\"telefono\":\"999\",\"correo_electronico\":\"ana@example.com\",\"salario_base\":2500.50}";

        client.post().uri("/api/v1/usuarios")
                .bodyValue(json)
                .exchange()
                .expectStatus().isCreated();
    }
}
