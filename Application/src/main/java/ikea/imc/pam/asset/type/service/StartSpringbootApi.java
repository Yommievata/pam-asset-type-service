package ikea.imc.pam.asset.type.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ConfigurationPropertiesScan("ikea.imc.pam.asset.type.service.configuration.properties")
@EnableCaching
public class StartSpringbootApi {
    public static void main(String[] args) {
        SpringApplication springbootApi = new SpringApplication(StartSpringbootApi.class);
        springbootApi.run(args);
    }
}
