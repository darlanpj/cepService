package com.cep.service

import com.cep.service.Config.MongoConfig
import com.github.tomakehurst.wiremock.junit.WireMockRule
import mockit.Deencapsulation
import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.util.SocketUtils
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification

import javax.annotation.PostConstruct

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
/**
 * Created by peo_djambersi on 31/03/2017.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(value = [DirtiesContextTestExecutionListener,
        DirtiesContextBeforeModesTestExecutionListener], mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@ContextConfiguration(classes = [AddressServiceApplication.class, MongoConfig.class])
abstract class AbstractSpecificationTest extends Specification{

    @Value('${local.server.port}')
    protected final int serverPort;

    protected final static Integer wiremockPort = SocketUtils.findAvailableTcpPort();

    @Shared
    @ClassRule
    WireMockRule wireMockRule = new WireMockRule(options().port(wiremockPort).containerThreads(15).jettyAcceptors(10))

    @Rule
    public final ExpectedException exception = ExpectedException.none()

    protected RestTemplate restTemplate

    def setup() {

        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            protected boolean hasError(HttpStatus statusCode) {
                return false;
            }
        });
    }

    @Configuration
    public static class MockConfig implements BeanPostProcessor {


        @PostConstruct
        public void wiremockInit() {
            if (wiremockPort != null) {
                System.setProperty("wiremockPort", wiremockPort.toString());
            }
        }

        @Override
        Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

                Deencapsulation.setField(o, "baseUrl", "http://localhost:$wiremockPort".toString())

            return o;
        }

        @Override
        Object postProcessAfterInitialization(Object o, String s) throws BeansException {
            return o;
        }
    }


}
