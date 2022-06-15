package nl.paulzijlmans.jdbc.model;

import org.springframework.data.annotation.Id;

public record Customer(@Id Integer id, String name) {
}
