package ru.libertyfirewall.backendapi.enumeration.rules;

public enum Action {
    DROP("drop"),
    ALERT("alert"),
    PASS("pass"),
    REJECT("reject");

    private final String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
