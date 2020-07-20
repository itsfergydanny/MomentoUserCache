# Description
Plugin/API to allow the storing or fetching of online/offline players 
most recent ign/uuid/head texture from their
last login on the server!

Reasoning: usually to get an offline players UUID you have to store it somewhere (file, database) or make an expensive (laggy) call to Bukkit.getOfflinePlayer

So you'd find yourself repeating what this code provides in every plugin that needs it, instead just have it in one and since its on MySQL, it can be used to keep a global cache of everyone across your network.

# Requirements
- Java8+
- This plugin requires MySQL because it was built around it. Feel free to adapt it to something else if need be.

# How to use
1. Download/Compile the plugin and add it to your server.
2. add `depend: [MomentoUserCache]` to your plugin.yml file (or softdepend).
3. Add the plugin as a dependency to your project.
4. Grab an instance of the plugin using `MomentoUserCache plugin = (MomentoUserCache) Bukkit.getServer().getPluginManager().getPlugin("MomentoUserCache")`
5. Use the instance to use APi, for example `plugin.getAPI().getHeadItem("fergydanny")`

# Notes
- Only updates the head once per login per server restart. (So its cached in memory until server is rebooted).
- It makes use of SkullUtils and XMaterial (https://github.com/CryptoMorin/XSeries) so it should already be pretty version independent.