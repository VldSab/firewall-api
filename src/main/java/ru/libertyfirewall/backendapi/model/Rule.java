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
    @NotEmpty(message = "Source port cannot be empty or null")
    private String srcPorts;
    private String dstIPs;
    private Long dstGroupID;
    @NotEmpty(message = "Destination port address cannot be empty or null")
    private String dstPorts;
    private AdditionalRuleParameters additionalRuleParameters;
    private String comment;

}
