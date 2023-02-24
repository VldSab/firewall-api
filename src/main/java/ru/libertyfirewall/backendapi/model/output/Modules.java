package ru.libertyfirewall.backendapi.model.output;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class Modules {
    /***
     * Абстракция над множеством модулей для возможности
     * передачи на ядро списка всех модулей единовременно.
     */
    private final List<Module> modules;

    public boolean addModule(Module module) {
        return modules.add(module);
    }

}
