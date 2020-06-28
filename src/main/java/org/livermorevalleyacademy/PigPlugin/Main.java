package org.livermorevalleyacademy.PigPlugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.livermorevalleyacademy.PigPlugin.commands.SummonPig;
import org.livermorevalleyacademy.PigPlugin.events.VehicleExit;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
    }

    public void registerCommands() {
        getCommand("pig").setExecutor(new SummonPig());
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new VehicleExit(), this);
    }
}
