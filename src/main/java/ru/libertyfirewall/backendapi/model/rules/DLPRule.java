package ru.libertyfirewall.backendapi.model.rules;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.libertyfirewall.backendapi.enumeration.rules.Action;
import ru.libertyfirewall.backendapi.enumeration.rules.Protocol;
import ru.libertyfirewall.backendapi.enumeration.rules.SensitiveCategory;
import ru.libertyfirewall.backendapi.model.GroupContainer;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dlp_rules")
public class DLPRule implements Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(name = "action")
    @NotNull
    private Action action;
    @Column(name = "protocol")
    @NotNull
    private Protocol protocol;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "src_group_id", referencedColumnName = "id", updatable = false)
    @NotNull
    private GroupContainer srcGroup;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dst_group_id", referencedColumnName = "id", updatable = false)
    @NotNull
    private GroupContainer dstGroup;
    @Column(name = "additional_parameters")
    private String additionalRuleParameters;
    @Column(name = "comment")
    private String comment;
    @Column(name = "sensitive_category", nullable = false)
    @NotNull
    private SensitiveCategory sensitiveCategory;

}
