package com.ezz.client.modules;

import com.ezz.client.gui.ClickGuiScreen;
import com.ezz.client.module.Module;
import net.minecraft.client.MinecraftClient;

public class ClickGuiModule extends Module {

    public ClickGuiModule() {
        super("ClickGUI", "Открывает меню модулей");
    }

    @Override
    protected void onEnable() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.currentScreen == null) {
            client.setScreen(new ClickGuiScreen());
        }
        setEnabled(false);
    }
}
