package ru.libertyfirewall.backendapi.exeptions.rule;

public class NoSuchRuleExeption extends Exception{
    public NoSuchRuleExeption() {
        super();
    }

    public NoSuchRuleExeption(String message) {
        super(message);
    }
}
