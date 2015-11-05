package tk.lenkyun.foodbook.server;

import org.springframework.core.env.Environment;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class Config {
    public static final long SESSION_SHORT = 60 * 60, // 1 hour
                             SESSION_LONG  = 3 * 24 * 60 * 60; // 3 days

    public static final String getDatabase(Environment environment){
        return "foodbooktest";
    }

    public static final String getUserTable(Environment environment){
        return "users";
    }
}
