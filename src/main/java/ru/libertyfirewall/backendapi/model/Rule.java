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

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    /**
     * Модель сущности правила Suricata.
     */
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotNull(message = "Action cannot be empty or null")
    private Action action;
    @NotNull(message = "Protocol cannot be empty or null")
    private Protocol protocol;
    private String srcIPs;
    private Long srcGroupID;
    private String srcPorts;
    private String dstIPs;
    private Long dstGroupID;
    private String dstPorts;
    private String additionalRuleParameters;
    private String comment;

}
