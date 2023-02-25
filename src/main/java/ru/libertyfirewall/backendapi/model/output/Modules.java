package ru.libertyfirewall.backendapi.model.output;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Data
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
