package com.dnyferguson.momentousercache.api;

import com.dnyferguson.momentousercache.MomentoUserCache;
import com.dnyferguson.momentousercache.utils.SkullUtils;
import com.dnyferguson.momentousercache.utils.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class API {
    private final MomentoUserCache plugin;

    public API(MomentoUserCache plugin) {
        this.plugin = plugin;
    }

    /**
     *
     * @param username
     * @return The UUID the specified player last logged on with
     */
    public UUID getUUID(String username) {
        if (plugin.getUsersByIgn().containsKey(username)) {
            return plugin.getUsersByIgn().get(username).getUuid();
        }
        return null;
    }

    /**
     *
     * @param uuid
     * @return The in-game name the specified player last logged on with
     */
    public String getIGN(UUID uuid) {
        if (plugin.getUsersByUUID().containsKey(uuid)) {
            return plugin.getUsersByUUID().get(uuid).getIgn();
        }
        return null;
    }

    /**
     *
     * @param username
     * @return The players head texture
     */
    public String getHeadTexture(String username) {
        if (plugin.getUsersByIgn().containsKey(username)) {
            return plugin.getUsersByIgn().get(username).getHeadTexture();
        }
        return null;
    }

    /**
     *
     * @param uuid
     * @return The players head texture
     */
    public String getHeadTexture(UUID uuid) {
        if (plugin.getUsersByUUID().containsKey(uuid)) {
            return plugin.getUsersByUUID().get(uuid).getHeadTexture();
        }
        return null;
    }

    /**
     *
     * @param username
     * @return A blank playerhead item with the texture of the specified player
     */
    public ItemStack getHeadItem(String username) {
        String headTexture = getHeadTexture(username);
        if (headTexture == null) {
            return null;
        }

        return getHead(headTexture);
    }

    /**
     *
     * @param uuid
     * @return A blank playerhead item with the texture of the specified player
     */
    public ItemStack getHeadItem(UUID uuid) {
        String headTexture = getHeadTexture(uuid);
        if (headTexture == null) {
            return null;
        }

        return getHead(headTexture);
    }

    private ItemStack getHead(String headTexture) {
        if (headTexture == null) {
            return null;
        }

        ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
        if (head != null) {
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta = SkullUtils.getSkullByValue(meta, headTexture);
            head.setItemMeta(meta);
        }

        return head;
    }
}
