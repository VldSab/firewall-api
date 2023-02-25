package ru.libertyfirewall.backendapi.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.libertyfirewall.backendapi.enumeration.modules.ModulesNames;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Module {
    /***
     * Модуль - абстракция отдельного файла сурикаты.
     * Каждый модуль содержит наззвание комонента файервола (Firewall, DLP, DPI),
     * название файла в который требуется запись и набор правил.
     */
    private ModulesNames name;
    private String fileName;
    private Set<String> ruleset;
}
