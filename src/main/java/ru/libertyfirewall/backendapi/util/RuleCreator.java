package ru.libertyfirewall.backendapi.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.repository.GroupRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RuleCreator implements RulesCreator {
    /**
     * Класс реализует преобразование правил в текстовые значения и
     * генерирует RulesStorage для последующей передачи в Suricata.
     */
    private final GroupRepository groupRepository;

    private String parseRule(Rule rule) {
        final String SPACE = " ";
        final String ARROW = "->";
        final String ANY = "any";
        String source;
        String destination;
        if (rule.getSrcIPs() == null) {
            GroupContainer group = groupRepository.getReferenceById(rule.getSrcGroupID());
            source = "[" + String.join(", ", group.getContainer()) + "]";
        } else {
            source = rule.getSrcIPs().equals(ANY) ? ANY : "[" + rule.getSrcIPs() + "]";
        }

        if (rule.getDstIPs() == null) {
            GroupContainer group = groupRepository.getReferenceById(rule.getDstGroupID());
            destination = "[" + String.join(", ", group.getContainer()) + "]";
        } else {
            destination = rule.getDstIPs().equals(ANY) ? ANY : "[" + rule.getDstIPs() + "]";
        }

        String srcPorts = rule.getSrcPorts().equals(ANY) ? ANY : "[" + rule.getSrcPorts() + "]";
        String dstPorts = rule.getDstPorts().equals(ANY) ? ANY : "[" + rule.getDstPorts() + "]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rule.getAction().getActionName()).append(SPACE)
                .append(rule.getProtocol().getProtocolName()).append(SPACE).append(source)
                .append(SPACE).append(srcPorts).append(SPACE).append(ARROW).append(SPACE)
                .append(destination).append(SPACE).append(dstPorts).append(SPACE);

        if (rule.getAdditionalRuleParameters() != null) stringBuilder.append(rule.getAdditionalRuleParameters());
        return stringBuilder.toString();
    }

    @Override
    public RulesStorage createRules(Rule rule) {
        return new RulesStorage(parseRule(rule));
    }

    @Override
    public RulesStorage createRules(List<Rule> rules) {
        Set<String> rulesSet = rules.stream().map(this::parseRule).collect(Collectors.toSet());
        return new RulesStorage(rulesSet);
    }
}
