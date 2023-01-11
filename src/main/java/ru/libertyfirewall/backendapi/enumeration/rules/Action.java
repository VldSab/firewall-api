package ru.libertyfirewall.backendapi.enumeration.rules;

public enum Action {
    DROP("DROP"),
    ALERT("ALERT"),
    PASS("PASS"),
    REJECT("REJECT");

    private final String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
