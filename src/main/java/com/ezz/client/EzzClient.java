package com.ezz.client;

import com.ezz.client.gui.ClickGuiScreen;
import com.ezz.client.hud.HudRenderer;
import com.ezz.client.module.Module;
import com.ezz.client.module.ModuleManager;
import com.ezz.client.modules.AutoRespawnModule;
import com.ezz.client.modules.AutoWalkModule;
import com.ezz.client.modules.ClickGuiModule;
import com.ezz.client.modules.FullBrightModule;
import com.ezz.client.modules.HudModule;
import com.ezz.client.modules.SprintModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class EzzClient implements ClientModInitializer {

    public static final String MOD_ID = "ezzclient";

    public static ModuleManager modules;

    private static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        modules = new ModuleManager();
        modules.register(new FullBrightModule());
        modules.register(new SprintModule());
        modules.register(new AutoWalkModule());
        modules.register(new AutoRespawnModule());
        modules.register(new HudModule());
        modules.register(new ClickGuiModule());

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ezzclient.open", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.ezzclient"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGuiScreen());
                }
            }
            for (Module module : modules.getAll()) {
                if (module.isEnabled()) {
                    module.onTick(client);
                }
            }
        });

        HudRenderCallback.EVENT.register(HudRenderer::render);
    }

    public static void handleChatCommand(String raw) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) {
            return;
        }
        String body = raw.substring(".ezz".length()).trim();
        if (body.isEmpty() || body.equals("help")) {
            client.player.sendMessage(Text.literal("§a[Ezz]§r команды: .ezz list | .ezz toggle <имя>"), false);
            return;
        }
        if (body.equals("list")) {
            StringBuilder sb = new StringBuilder("§a[Ezz]§r модули:");
            for (Module m : modules.getAll()) {
                sb.append(" ").append(m.getName()).append(m.isEnabled() ? " §a[ВКЛ]" : " §7[ВЫКЛ]");
            }
            client.player.sendMessage(Text.literal(sb.toString()), false);
            return;
        }
        if (body.startsWith("toggle ")) {
            String name = body.substring(7).trim();
            Module m = modules.getByName(name);
            if (m == null) {
                client.player.sendMessage(Text.literal("§c[Ezz]§r модуль не найден: " + name), false);
            } else {
                m.toggle();
                client.player.sendMessage(Text.literal("§a[Ezz]§r " + m.getName() + " → " + (m.isEnabled() ? "ВКЛ" : "ВЫКЛ")), false);
            }
            return;
        }
        client.player.sendMessage(Text.literal("§c[Ezz]§r неизвестная команда: " + body), false);
    }
}
