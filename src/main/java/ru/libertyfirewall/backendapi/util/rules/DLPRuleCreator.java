package ru.libertyfirewall.backendapi.util.rules;

import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.model.rules.DLPRule;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DLPRuleCreator implements RulesCreator<DLPRule> {
    private final Long OFFSET_OVER_FIREWALL_INDEXES = 1_000_000L;
    @Override
    public String parseRule(DLPRule rule) {

        final String SPACE = " ";
        final String ARROW = "->";
        String sourceIps = "[" + String.join(", ", rule.getSrcGroup().getIpContainer()) + "]";
        String sourcePorts = "[" + String.join(", ", rule.getSrcGroup().getPortContainer()) + "]";
        String destinationIps = "[" + String.join(", ", rule.getDstGroup().getIpContainer()) + "]";
        String destinationPorts = "[" + String.join(", ", rule.getDstGroup().getPortContainer()) + "]";
        String sid = "sid: " + (rule.getId() + OFFSET_OVER_FIREWALL_INDEXES);
        String message = "msg: " + rule.getComment();
        String sensitiveCategory = "dlp." + rule.getSensitiveCategory().suricataName();
        String additionalParameters = "(" + sid + ";" + message + ";" + sensitiveCategory + ")";
        StringBuilder ruleBuilder = new StringBuilder();
        return ruleBuilder.append(rule.getAction()).append(SPACE).append(rule.getProtocol()).append(SPACE)
                .append(sourceIps).append(SPACE).append(sourcePorts).append(SPACE).append(ARROW).append(SPACE)
                .append(destinationIps).append(SPACE).append(destinationPorts).append(SPACE).append(additionalParameters)
                .toString();

    }

    @Override
    public RulesStorage createRules(DLPRule dlpRule) {
        return new RulesStorage(parseRule(dlpRule));
    }

    @Override
    public RulesStorage createRules(List<DLPRule> dlpRules) {
        Set<String> rulesSet = dlpRules.stream().map(this::parseRule).collect(Collectors.toSet());
        return new RulesStorage(rulesSet);
    }
}
