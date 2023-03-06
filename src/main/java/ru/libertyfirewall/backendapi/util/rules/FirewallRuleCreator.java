package ru.libertyfirewall.backendapi.util.rules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.rules.FirewallRule;
import ru.libertyfirewall.backendapi.repository.GroupRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FirewallRuleCreator implements RulesCreator<FirewallRule> {
    /**
     * Класс реализует преобразование правил в текстовые значения и
     * генерирует RulesStorage для последующей передачи в Suricata.
     */
    private final GroupRepository groupRepository;

    private String parseRule(FirewallRule firewallRule) {
        final String SPACE = " ";
        final String ARROW = "->";
        final String ANY = "any";
        String source;
        String destination;
        String srcPorts;
        String dstPorts;
        String sid;

        if (firewallRule.getSrcIP() == null) {
//            GroupContainer srcGroup = groupRepository.getReferenceById(firewallRule.getSrcGroup().getId());
//            firewallRule.setSrcGroup(srcGroup);
            GroupContainer srcGroup = firewallRule.getSrcGroup();
            source = "[" + String.join(", ", srcGroup.getIpContainer()) + "]";
            srcPorts = "[" + String.join(", ", srcGroup.getPortContainer()) + "]";
        } else {
            source = firewallRule.getSrcIP().equals(ANY) ? ANY : "[" + firewallRule.getSrcIP() + "]";
            srcPorts = firewallRule.getSrcPort().equals(ANY) ? ANY : "[" + firewallRule.getSrcPort() + "]";

        }

        if (firewallRule.getDstIP() == null) {
//            GroupContainer dstGroup = groupRepository.getReferenceById(firewallRule.getDstGroup().getId());
//            firewallRule.setSrcGroup(dstGroup);
            GroupContainer dstGroup = firewallRule.getDstGroup();
            destination = "[" + String.join(", ", dstGroup.getIpContainer()) + "]";
            dstPorts = "[" + String.join(", ", dstGroup.getPortContainer()) + "]";
        } else {
            destination = firewallRule.getDstIP().equals(ANY) ? ANY : "[" + firewallRule.getDstIP() + "]";
            dstPorts = firewallRule.getDstPort().equals(ANY) ? ANY : "[" + firewallRule.getDstPort() + "]";
        }

        sid = "(sid: " + firewallRule.getId().toString() + ";)";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firewallRule.getAction().getActionName()).append(SPACE)
                .append(firewallRule.getProtocol().getProtocolName()).append(SPACE).append(source)
                .append(SPACE).append(srcPorts).append(SPACE).append(ARROW).append(SPACE)
                .append(destination).append(SPACE).append(dstPorts).append(SPACE).append(sid);

        if (firewallRule.getAdditionalRuleParameters() != null) stringBuilder.append(firewallRule.getAdditionalRuleParameters());
        return stringBuilder.toString();
    }

    @Override
    public RulesStorage createRules(FirewallRule firewallRule) {
        return new RulesStorage(parseRule(firewallRule));
    }

    @Override
    public RulesStorage createRules(List<FirewallRule> firewallRules) {
        Set<String> rulesSet = firewallRules.stream().map(this::parseRule).collect(Collectors.toSet());
        return new RulesStorage(rulesSet);
    }
}
