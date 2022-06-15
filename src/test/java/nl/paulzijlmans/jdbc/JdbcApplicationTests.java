package nl.paulzijlmans.jdbc;

import nl.paulzijlmans.jdbc.model.Customer;
import nl.paulzijlmans.jdbc.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class JdbcApplicationTests {

  @Container
  public static PostgreSQLContainer<?> psql = new PostgreSQLContainer<>("postgres:12");

  @DynamicPropertySource
  static void configureTestcontainersProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", psql::getJdbcUrl);
    registry.add("spring.datasource.username", psql::getUsername);
    registry.add("spring.datasource.password", psql::getPassword);
  }

  @Autowired
  public CustomerRepository customerRepository;

  @Test
  void contextLoads() {
    Assertions.assertFalse(customerRepository.findAll().iterator().hasNext(),
        "there should be no data");
    var customer = customerRepository.save(new Customer(null, "John"));
    Assertions.assertTrue(customerRepository.findAll().iterator().hasNext(),
        "there should be some data");
  }
}
