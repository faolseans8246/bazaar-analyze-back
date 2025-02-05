package org.example.bazaaranalyze.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.bazaaranalyze.repository")
public class JpaConf {
}
