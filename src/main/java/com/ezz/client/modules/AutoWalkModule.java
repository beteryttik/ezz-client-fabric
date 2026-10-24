package com.ezz.client.modules;

import com.ezz.client.module.Module;
import net.minecraft.client.MinecraftClient;

public class AutoWalkModule extends Module {

    public AutoWalkModule() {
        super("AutoWalk", "Сам идёт вперёд");
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player != null && client.currentScreen == null) {
            client.options.forwardKey.setPressed(true);
        }
    }
}
