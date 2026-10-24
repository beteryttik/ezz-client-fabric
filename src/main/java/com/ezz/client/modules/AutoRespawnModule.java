package com.ezz.client.modules;

import com.ezz.client.module.Module;
import net.minecraft.client.MinecraftClient;

public class AutoRespawnModule extends Module {
    private int cooldown;

    public AutoRespawnModule() {
        super("AutoRespawn", "Автоматически возрождает после смерти");
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (cooldown > 0) {
            cooldown--;
            return;
        }
        if (client.player != null && client.player.isDead() && client.getNetworkHandler() != null) {
            client.getNetworkHandler().sendCommand("respawn");
            cooldown = 40;
        }
    }
}
