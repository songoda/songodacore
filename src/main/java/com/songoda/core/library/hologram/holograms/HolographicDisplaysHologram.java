package com.songoda.core.library.hologram.holograms;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class HolographicDisplaysHologram extends Hologram {

    public HolographicDisplaysHologram(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "HolographicDisplays";
    }

    @Override
    public void add(Location location, List<String> lines) {
        fixLocation(location);

        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = HologramsAPI.createHologram(plugin, location);
        for (String line : lines) {
            hologram.appendTextLine(line);
        }
    }

    @Override
    public void remove(Location location) {
        fixLocation(location);
        for (com.gmail.filoghost.holographicdisplays.api.Hologram hologram : HologramsAPI.getHolograms(plugin)) {
            if (hologram.getX() != location.getX()
                    || hologram.getY() != location.getY()
                    || hologram.getZ() != location.getZ()) continue;
            hologram.delete();
        }
    }

    @Override
    public void update(Location location, List<String> lines) {
        for (com.gmail.filoghost.holographicdisplays.api.Hologram hologram : HologramsAPI.getHolograms(plugin)) {
            if (hologram.getX() != location.getX()
                    || hologram.getY() != location.getY()
                    || hologram.getZ() != location.getZ()) continue;
            fixLocation(location);
            hologram.clearLines();
            for (String line : lines) {
                hologram.appendTextLine(line);
            }
            return;
        }
        add(location, lines);
    }
}
