package com.bekvon.bukkit.residence.utils;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class ResScheduler {

    public static ScheduledTask runTaskAsynchronously(Plugin plugin, Runnable task){
        return Bukkit.getAsyncScheduler().runNow(plugin, (t) -> task.run());
    }

    public static ScheduledTask runTask(Plugin plugin, Runnable task){
        return Bukkit.getGlobalRegionScheduler().run(plugin , (t) -> task.run());
    }

    public static ScheduledTask scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay){
        return Bukkit.getGlobalRegionScheduler().runDelayed(plugin, (t) -> task.run(), delay != 0 ? delay : 1 );
    }

    public static ScheduledTask scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period){
        return Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, (t) -> task.run(), delay != 0 ? delay : 1 , period);
    }

    public static ScheduledTask scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period){
        return Bukkit.getAsyncScheduler().runAtFixedRate(plugin, (t) -> task.run(), delay != 0 ? delay * 50 : 1 , period * 50, TimeUnit.MILLISECONDS);
    }

    public static ScheduledTask runTaskLater(Plugin plugin, Runnable task, long delay){
        return scheduleSyncDelayedTask(plugin, task, delay);
    }

    public static void cancelTask(ScheduledTask task){
        if (task != null) {
            task.cancel();
        }
    }
}
