package ru.libertyfirewall.backendapi.exeptions.group;

import ru.libertyfirewall.backendapi.exeptions.NotFoundException;

public class NoSuchGroupException extends NotFoundException {
    public NoSuchGroupException() {
        super();
    }

    public NoSuchGroupException(String message) {
        super(message);
    }
}
