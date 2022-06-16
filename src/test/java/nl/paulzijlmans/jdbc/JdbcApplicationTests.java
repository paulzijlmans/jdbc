package nl.paulzijlmans.jdbc;

import nl.paulzijlmans.jdbc.model.Customer;
import nl.paulzijlmans.jdbc.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Profile("test")
class JdbcApplicationTests {

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
