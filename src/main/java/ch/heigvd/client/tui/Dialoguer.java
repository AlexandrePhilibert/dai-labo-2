package ch.heigvd.client.tui;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Utility class, as a nicer logback alternative
 * <br/>
 * mproved version of <a href="https://github.com/AlexandrePhilibert/dai-labo-1/blob/main/src/main/java/ch/kodai/templ8/Dialoguer.java">dai-labo-1's Dialoguer</a>
 */
public class Dialoguer {

    public static void showProgress(String message) {
        // This is meant for nerdfont terminals
        System.out.println(colorize("💠 ", Attribute.BLUE_TEXT(), Attribute.BOLD()) + colorize(message, Attribute.WHITE_TEXT()));
    }

    public static void showSuccess(String message) {
        System.out.println(colorize("✅ ", Attribute.BRIGHT_GREEN_TEXT(), Attribute.BOLD()) + colorize(message, Attribute.WHITE_TEXT()));
    }

    public static void showError(String error) {
        System.out.println(colorize("❌ ", Attribute.BRIGHT_RED_TEXT(), Attribute.BOLD()) + colorize(error, Attribute.RED_TEXT()));
    }

    public static void showInfo(String message) {
        System.out.println(colorize("⭕ ", Attribute.BRIGHT_BLUE_TEXT(), Attribute.BOLD()) + colorize(message, Attribute.WHITE_TEXT()));
    }

}