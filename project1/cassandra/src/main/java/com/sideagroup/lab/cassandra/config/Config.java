package com.sideagroup.lab.cassandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class Config {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;

    /**
     * This is necessary to have the Spring Boot app use Astra secure bundle
     * to connect to the database.
     * @param astraProperties
     * @return
     */
    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(
            DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder
                .withCloudSecureConnectBundle(bundle)
                .withAuthCredentials(username, password)
                .withKeyspace(keyspace);
    }
}
