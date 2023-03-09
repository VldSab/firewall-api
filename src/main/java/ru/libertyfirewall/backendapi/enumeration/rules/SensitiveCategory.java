package ru.libertyfirewall.backendapi.enumeration.rules;

public enum SensitiveCategory {
    CARD_NUMBERS("card_numbers"),
    FULL_NAMES("full_names"),
    SNILSES("snilses");

    private final String suricataName;

    SensitiveCategory(String suricataName) {
        this.suricataName = suricataName;
    }
    public String suricataName() {
        return suricataName;
    }
}
