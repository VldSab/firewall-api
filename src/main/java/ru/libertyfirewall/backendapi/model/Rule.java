package ru.libertyfirewall.backendapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.libertyfirewall.backendapi.enumeration.rules.Action;
import ru.libertyfirewall.backendapi.enumeration.rules.Protocol;
import ru.libertyfirewall.backendapi.util.AdditionalRuleParameters;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotNull(message = "Action cannot be empty or null")
    private Action action;
    @NotNull(message = "Protocol cannot be empty or null")
    private Protocol protocol;
    @NotEmpty(message = "Source IP cannot be empty or null")
    private String srcIP;
    @NotEmpty(message = "Source port cannot be empty or null")
    private String srcPort;
    @NotEmpty(message = "Destination IP cannot be empty or null")
    private String dstIP;
    @NotEmpty(message = "Destination port address cannot be empty or null")
    private String dstPort;
    private String comment;
    private AdditionalRuleParameters additionalRuleParameters;
}
