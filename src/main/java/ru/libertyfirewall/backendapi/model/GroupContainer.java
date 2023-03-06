package ru.libertyfirewall.backendapi.model;

import jakarta.persistence.*;
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
@Table(name = "groups")
public class GroupContainer {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Name can not be empty")
    @Column(name = "name")
    private String name;
    @ElementCollection
    @CollectionTable(
            name = "ip_container",
            joinColumns = @JoinColumn(name = "group_id")
    )
    @Column(name = "ip")
    private Collection<String> ipContainer;
    @ElementCollection
    @CollectionTable(
            name = "port_container",
            joinColumns = @JoinColumn(name = "group_id")
    )
    @Column(name = "port")
    private Collection<String> portContainer;
    @Column(name = "comment")
    private String comment;
}
