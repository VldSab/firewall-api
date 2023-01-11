package ru.libertyfirewall.backendapi.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupContainer {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @ElementCollection
    private Collection<String> container;
}
