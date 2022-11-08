package com.reactive.servicereactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.extern.slf4j.Slf4j;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Slf4j
@Configuration
public class R2dbcConfiguration {
    

    @Bean
    public ConnectionFactory connectionFactory(){
        log.info("database reactive..");
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
            .option(DRIVER, "postgresql")
            .option(HOST, "localhost")
            .option(PORT, 5432)
            .option(USER, "postgres")
            .option(PASSWORD, "root")
            .option(DATABASE, "dbr2dbc")
            .option(MAX_SIZE, 40)
            .build());
    }

}
