package ch.heigvd.server.utils;

import java.time.Instant;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IdProvider {
    public static String getId() {
        byte[] contents = new byte[14];
        Random random = ThreadLocalRandom.current();
        random.nextBytes(contents);
        return Base64.getEncoder().encodeToString(contents);
    }
}
