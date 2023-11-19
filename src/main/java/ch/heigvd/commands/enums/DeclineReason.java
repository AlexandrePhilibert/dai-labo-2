package ch.heigvd.commands.enums;

import java.util.Arrays;

public enum DeclineReason {
    USERNAME_TAKEN("username_taken", "Username already taken."),
    ACCOUNT_LOCKED("account_locked", "The account has been locked by an administrator."),
    IP_INVALID("ip_invalid", "You cannot access this account with your current IP."),
    ALREADY_LOGGED_IN("already_logged_in", "You are already connected."),
    UNSUPPORTED_COMMAND("unsupported_command", "Unsupported command."),
    NOT_CONNECTED("not_connected", "You need to be connected to run this command"),
    INVALID_COMMAND("invalid_command", "Unknown command, or invalid command format"),
    CUSTOM()
    ;

    private final String code;
    private final String defaultMessage;

    DeclineReason(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    DeclineReason() {
        code = "";
        defaultMessage = "Unknown message";
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public static DeclineReason from(String code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(CUSTOM);
    }
}
