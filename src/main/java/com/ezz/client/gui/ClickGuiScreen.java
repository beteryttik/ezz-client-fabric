package com.ezz.client.gui;

import com.ezz.client.module.Module;
import com.ezz.client.EzzClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen {

    public ClickGuiScreen() {
        super(Text.literal("Ezz Client — модули"));
    }

    @Override
    protected void init() {
        int y = 30;
        for (Module module : EzzClient.modules.getAll()) {
            ButtonWidget button = ButtonWidget.builder(
                    Text.literal(module.getName() + ": " + (module.isEnabled() ? "ВКЛ" : "ВЫКЛ")),
                    b -> {
                        module.toggle();
                        b.setMessage(Text.literal(module.getName() + ": " + (module.isEnabled() ? "ВКЛ" : "ВЫКЛ")));
                    }
            ).dimensions(this.width / 2 - 100, y, 200, 20).build();
            addDrawableChild(button);
            y += 24;
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void close() {
        if (client != null) {
            client.setScreen(null);
        }
    }
}
