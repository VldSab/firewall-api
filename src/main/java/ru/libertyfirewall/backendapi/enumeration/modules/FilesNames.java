package ru.libertyfirewall.backendapi.enumeration.modules;

public enum FilesNames {
    FIREWALL("Firewall.rules"),
    DLP("DLP.rules");

    private final String filename;

    FilesNames(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

}
