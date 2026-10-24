package com.ezz.client.hud;

import com.ezz.client.EzzClient;
import com.ezz.client.module.Module;
import com.ezz.client.modules.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

public final class HudRenderer {

    private HudRenderer() {
    }

    public static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        HudModule hud = EzzClient.modules.get(HudModule.class);
        if (hud == null || !hud.isEnabled()) {
            return;
        }
        if (client.player == null || client.options.hudHidden) {
            return;
        }

        int accent = 0xFF33FF66;
        int white = 0xFFFFFF;
        int y = 4;

        context.drawTextWithShadow(client.textRenderer, Text.literal("Ezz Client"), 4, y, accent);
        y += 10;
        context.drawTextWithShadow(client.textRenderer, Text.literal("FPS: " + client.getCurrentFps()), 4, y, white);
        y += 10;
        context.drawTextWithShadow(client.textRenderer, Text.literal("XYZ: " + client.player.getBlockPos().toShortString()), 4, y, white);
        y += 10;
        context.drawTextWithShadow(client.textRenderer, Text.literal("Модули:"), 4, y, accent);
        y += 10;
        for (Module m : EzzClient.modules.getAll()) {
            if (m.isEnabled()) {
                context.drawTextWithShadow(client.textRenderer, Text.literal("> " + m.getName()), 4, y, white);
                y += 10;
            }
        }
    }
}
