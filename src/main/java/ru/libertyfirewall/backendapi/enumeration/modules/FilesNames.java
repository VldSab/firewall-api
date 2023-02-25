package ru.libertyfirewall.backendapi.enumeration.modules;

public enum FilesNames {
    FIREWALL("Firewall.rules"),
    DLP("DLP.rules");

    FilesNames(String filename) {}

    private String filename;

    public String getFilename() {
        return filename;
    }

}
