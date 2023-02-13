package ru.libertyfirewall.backendapi.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GroupContainer {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @ElementCollection
    private Collection<String> ipContainer;
    @ElementCollection
    private Collection<String> portContainer;
    private String comment;
}
