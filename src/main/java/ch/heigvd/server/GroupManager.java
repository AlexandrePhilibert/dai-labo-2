package ch.heigvd.server;

import java.util.concurrent.ConcurrentHashMap;

public class GroupManager {
    private static final ConcurrentHashMap<String, Group> groupMap = new ConcurrentHashMap<>();

    public static Group getGroup(String name) {
        return groupMap.computeIfAbsent(name, Group::new);
    }

    public static boolean exists(String name) {
        return groupMap.containsKey(name);
    }
}