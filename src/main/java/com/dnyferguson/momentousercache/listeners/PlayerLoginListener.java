package com.dnyferguson.momentousercache.listeners;

import com.dnyferguson.momentousercache.MomentoUserCache;
import com.dnyferguson.momentousercache.mysql.FindResultCallback;
import com.dnyferguson.momentousercache.objects.User;
import com.dnyferguson.momentousercache.utils.SkullUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerLoginListener implements Listener {
    private final MomentoUserCache plugin;

    public PlayerLoginListener(MomentoUserCache plugin) {
        this.plugin = plugin;
    }

    @EventHandler (ignoreCancelled = true)
    public void onPlayerLogin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        plugin.getSql().getResultAsync("SELECT * FROM `users` WHERE `uuid` = '" + player.getUniqueId() + "'", new FindResultCallback() {
            @Override
            public void onQueryDone(ResultSet result) throws SQLException {
                String headTexture;
                if (result.next()) {
                    headTexture = result.getString("head_texture");
                    if (!plugin.getLoggedInToday().containsKey(player.getUniqueId())) {
                        headTexture = SkullUtils.getSkinValue(player.getUniqueId().toString(), true);
                    }
                    plugin.getSql().executeStatementAsync("UPDATE `users` SET `ign`='" + player.getName() + "',`head_texture`='" + headTexture + "',`last_updated`=CURRENT_TIMESTAMP WHERE `uuid` = '" + player.getUniqueId() + "'");
                } else {
                    headTexture = SkullUtils.getSkinValue(player.getUniqueId().toString(), true);
                    plugin.getSql().executeStatementAsync("INSERT INTO `users` (`uuid`, `ign`, `head_texture`, `last_updated`) VALUES ('" + player.getUniqueId() + "', '" + player.getName() + "', '" + headTexture + "', CURRENT_TIMESTAMP)");
                }

                User user = new User(player.getUniqueId(), player.getName(), headTexture);
                plugin.getUsersByIgn().put(player.getName(), user);
                plugin.getUsersByUUID().put(player.getUniqueId(), user);
                plugin.getLoggedInToday().put(player.getUniqueId(), true);
            }
        });
    }
}
