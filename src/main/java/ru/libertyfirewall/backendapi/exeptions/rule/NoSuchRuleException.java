package ru.libertyfirewall.backendapi.exeptions.rule;

import ru.libertyfirewall.backendapi.exeptions.NotFoundException;

public class NoSuchRuleException extends NotFoundException {
    public NoSuchRuleException() {
        super();
    }

    public NoSuchRuleException(String message) {
        super(message);
    }
}
