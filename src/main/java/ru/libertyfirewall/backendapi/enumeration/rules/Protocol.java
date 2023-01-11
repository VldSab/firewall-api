package ru.libertyfirewall.backendapi.enumeration.rules;

public enum Protocol {
    TCP("TCP"),
    UDP("UDP"),
    HTTP("HTTP"),
    FTP("FTP"),
    TLS("TLS"),
    DNS("DNS"),
    SSH("SSH");

    private final String protocolName;

    Protocol(String protocolName) {
        this.protocolName = protocolName;
    }
}
