package ru.libertyfirewall.backendapi.model.rules;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.libertyfirewall.backendapi.enumeration.rules.Action;
import ru.libertyfirewall.backendapi.enumeration.rules.Protocol;
import ru.libertyfirewall.backendapi.model.GroupContainer;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "firewall_rules")
public class FirewallRule implements Rule{
    /**
     * Модель сущности правила Suricata.
     */
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "action")
    @NotNull(message = "Action cannot be empty or null")
    private Action action;
    @Column(name = "protocol")
    @NotNull(message = "Protocol cannot be empty or null")
    private Protocol protocol;
    @Column(name = "src_ip")
    private String srcIP;
    @Column(name = "src_port")
    private String srcPort;
    @Column(name = "dst_ip")
    private String dstIP;
    @Column(name = "dst_port")
    private String dstPort;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "src_group_id", referencedColumnName = "id", updatable=false)
    private GroupContainer srcGroup;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dst_group_id", referencedColumnName = "id", updatable=false)
    private GroupContainer dstGroup;
    @Column(name = "additional_parameters")
    private String additionalRuleParameters;
    @Column(name = "comment")
    private String comment;

}
