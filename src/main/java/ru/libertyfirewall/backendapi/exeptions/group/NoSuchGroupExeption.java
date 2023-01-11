package ru.libertyfirewall.backendapi.exeptions.group;

public class NoSuchGroupExeption extends Exception{
    public NoSuchGroupExeption() {
        super();
    }

    public NoSuchGroupExeption(String message) {
        super(message);
    }
}
