package ru.libertyfirewall.backendapi.enumeration.rules;

public enum Protocol {
    TCP("tcp"),
    UDP("udp"),
    HTTP("http"),
    FTP("ftp"),
    TLS("tls"),
    DNS("dns"),
    SSH("ssh");

    private final String protocolName;

    Protocol(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolName() {
        return protocolName;
    }
}
