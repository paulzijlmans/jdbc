package nl.paulzijlmans.jdbc.repository;

import nl.paulzijlmans.jdbc.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
