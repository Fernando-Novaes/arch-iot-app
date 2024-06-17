package br.ufrj.cos;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.ufrj.cos.repository")
@ComponentScan("br.ufrj.cos.service")
public class AppConfig {
    // Configuration beans if any
}
