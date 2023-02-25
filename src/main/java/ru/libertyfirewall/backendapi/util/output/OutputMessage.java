package ru.libertyfirewall.backendapi.util.output;

import com.google.gson.Gson;
import ru.libertyfirewall.backendapi.model.output.Modules;

public class OutputMessage {
    private static final Gson gson = new Gson();
    public static String createMessage(Modules modules) {
        return gson.toJson(modules);
    }
}
