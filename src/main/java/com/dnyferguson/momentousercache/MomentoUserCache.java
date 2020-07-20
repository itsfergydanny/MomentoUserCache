package com.dnyferguson.momentousercache;

import com.dnyferguson.momentousercache.api.API;
import com.dnyferguson.momentousercache.listeners.PlayerLoginListener;
import com.dnyferguson.momentousercache.mysql.FindResultCallback;
import com.dnyferguson.momentousercache.mysql.MySQL;
import com.dnyferguson.momentousercache.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public final class MomentoUserCache extends JavaPlugin {
    private MySQL sql;
    private final Map<UUID, User> usersByUUID = new ConcurrentHashMap<>();
    private final Map<String, User> usersByIgn = new ConcurrentHashMap<>();
    private final Map<UUID, Boolean> loggedInToday = new ConcurrentHashMap<>();
    private API api;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        sql = new MySQL(this);
        api = new API(this);
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);

        MomentoUserCache plugin = (MomentoUserCache) Bukkit.getServer().getPluginManager().getPlugin("MomentoUserCache");
        plugin.getApi();

        sql.getResultAsync("SELECT * FROM `users`", new FindResultCallback() {
            @Override
            public void onQueryDone(ResultSet result) throws SQLException {
                logInfo("Loading user cache");
                int count = 0;
                while (result.next()) {
                    try {
                        UUID uuid = UUID.fromString(result.getString("uuid"));
                        String ign = result.getString("ign");
                        User user = new User(uuid, ign, result.getString("head_texture"));
                        usersByUUID.put(uuid, user);
                        usersByIgn.put(ign, user);
                        count++;
                    } catch (Exception ignore) {}
                }
                logInfo("Loaded " + count + " users");
            }
        });
    }

    @Override
    public void onDisable() {
        if (sql != null) {
            sql.close();
        }
    }

    private void logInfo(String msg) {
        getLogger().log(Level.INFO, msg);
    }

    public MySQL getSql() {
        return sql;
    }

    public Map<UUID, User> getUsersByUUID() {
        return usersByUUID;
    }

    public Map<String, User> getUsersByIgn() {
        return usersByIgn;
    }

    public Map<UUID, Boolean> getLoggedInToday() {
        return loggedInToday;
    }

    public API getApi() {
        return api;
    }
}
