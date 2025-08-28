package co.com.autenticacion.config;

import co.com.autenticacion.application.usecase.RegistrarUsuarioUseCase;
import co.com.autenticacion.domain.port.UsuarioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(UsuarioRepositoryPort repo) {
        return new RegistrarUsuarioUseCase(repo);
    }
}
