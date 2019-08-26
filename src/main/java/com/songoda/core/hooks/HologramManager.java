package com.songoda.core.hooks;

import com.songoda.core.hooks.holograms.Holograms;
import org.bukkit.Location;
import java.util.List;
import org.bukkit.plugin.Plugin;

/**
 * A convenience class for static access to a Holograms HookManager
 */
public class HologramManager {

    private static final HookManager<Holograms> manager = new HookManager(Holograms.class);

    /**
     * Load all supported economy plugins.<br/>
	 * Note: This method should be called in your plugin's onEnable() section
	 * 
	 * @param plugin plugin that will be using the holograms
     */
    public static void load(Plugin plugin) {
        manager.load(plugin);
    }

    public static HookManager getManager() {
        return manager;
    }

    /**
     * Grab the default hologram plugin. <br />
     * NOTE: using a default hologram assumes that this library is shaded
     *
     * @return returns null if no plugin enabled
     */
    public static Holograms getHolograms() {
        return manager.getCurrentHook();
    }

    public static void createHologram(Location location, String line) {
        if (manager.isEnabled())
            manager.getCurrentHook().createHologram(location, line);
    }

    public static void createHologram(Location location, List<String> lines) {
        if (manager.isEnabled())
            manager.getCurrentHook().createHologram(location, lines);
    }

    public static void removeHologram(Location location) {
        if (manager.isEnabled())
            manager.getCurrentHook().removeHologram(location);
    }

    public static void removeAllHolograms() {
        if (manager.isEnabled())
            manager.getCurrentHook().removeAllHolograms();
    }

    public static void updateHologram(Location location, String line) {
        if (manager.isEnabled())
            manager.getCurrentHook().updateHologram(location, line);
    }

    public static void updateHologram(Location location, List<String> lines) {
        if (manager.isEnabled())
            manager.getCurrentHook().updateHologram(location, lines);
    }
}
