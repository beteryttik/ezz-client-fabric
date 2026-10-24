package com.ezz.client.modules;

import com.ezz.client.module.Module;
import net.minecraft.client.MinecraftClient;

public class SprintModule extends Module {

    public SprintModule() {
        super("Sprint", "Постоянный спринт при движении вперёд");
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player != null && client.player.input != null && client.player.input.pressingForward) {
            client.player.setSprinting(true);
        }
    }
}
