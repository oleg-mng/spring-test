package com.olegmng.app.api;

import com.olegmng.app.model.Customer;
import com.olegmng.app.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CustomerRepository customerRepository;

    @LocalServerPort
    int port;

    @Test
    public void testByIdNotFound() {
        webTestClient.get()
                .uri("/api/customer/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testGetById() {
        Customer random = customerRepository.save(Customer.ofName("random"));

        CustomerResponse responseBody = webTestClient.get()
                .uri("/api/customer/" + random.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(random.getId(), responseBody.getId());
        Assertions.assertEquals(random.getName(), responseBody.getName());
    }

    @Test
    public void testGetAll() {
        customerRepository.saveAll(List.of(
                Customer.ofName("first"),
                Customer.ofName("second")
        ));
        List<Customer> expected = customerRepository.findAll();
        List<CustomerResponse> responseBody = webTestClient.get()
                .uri("/api/customer")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerResponse>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (CustomerResponse customerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), customerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), customerResponse.getName()));
            Assertions.assertTrue(found);
        }
    }

}