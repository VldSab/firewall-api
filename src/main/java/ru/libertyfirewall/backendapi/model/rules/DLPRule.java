package ru.libertyfirewall.backendapi.model.rules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.libertyfirewall.backendapi.enumeration.rules.Action;
import ru.libertyfirewall.backendapi.enumeration.rules.Protocol;
import ru.libertyfirewall.backendapi.enumeration.rules.SensitiveCategory;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DLPRule implements Rule{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private Action action;
    private Protocol protocol;
    private Long srcGroupID;
    private Long dstGroupID;
    private String additionalRuleParameters;
    private String comment;
    private SensitiveCategory sensitiveCategory;

}
