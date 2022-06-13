package com.ikea.imc.pam.asset.type.service.component.test;

import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.File;
import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = AbstractBaseTest.TestConfig.class)
public abstract class AbstractBaseTest {
    protected TestData testData;

    @Value("${com.ikea.imc.pam.network.port}")
    private int assetTypeServicePort;

    @Value("${com.ikea.imc.pam.asset.type.service.docker.file.location}")
    private String dockerFileLocation;

    @Value("${com.ikea.imc.pam.asset.type.service.docker.container.name}")
    private String assetTypeServiceContainerName;

    @Value("${com.ikea.imc.pam.asset.type.service.docker.standalone:false}")
    private boolean dockerStandalone;

    @Value("${com.ikea.imc.pam.asset.type.service.wiremock.host}")
    private String wiremockHost;

    @Value("${com.ikea.imc.pam.asset.type.service.wiremock.port}")
    private int wiremockPort;

    private static DockerComposeContainer container;

    @Configuration
    @ComponentScan("com.ikea.imc.pam.asset.type.service")
    public static class TestConfig {

        @Bean
        OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager() {
            return new OAuth2AuthorizedClientManager() {
                @Override
                public OAuth2AuthorizedClient authorize(OAuth2AuthorizeRequest authorizeRequest) {
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void init() {
        log.debug("Application handled by component test: {}", dockerStandalone);

        WireMock.configureFor(wiremockHost, wiremockPort);
        WireMock.reset();
    }

    @BeforeEach
    public void setup() {
        // We share the docker instance between tests to speed up how long it takes to run the suite
        if (!dockerStandalone && container == null) {
            container =
                    new DockerComposeContainer(new File(dockerFileLocation))
                            .withRemoveImages(DockerComposeContainer.RemoveImages.ALL)
                            .withExposedService(assetTypeServiceContainerName, assetTypeServicePort, Wait.forHealthcheck())
                            .withLogConsumer(assetTypeServiceContainerName, new Slf4jLogConsumer(log));
            container.start();
        }
        testData = new TestData();
    }
}
