package com.ezz.client.modules;

import com.ezz.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;

public class FullBrightModule extends Module {
    private double previousGamma = 1.0;

    public FullBrightModule() {
        super("FullBright", "Убирает темноту");
    }

    @Override
    protected void onEnable() {
        SimpleOption<Double> gamma = MinecraftClient.getInstance().options.getGamma();
        previousGamma = gamma.getValue();
        gamma.setValue(15.0);
    }

    @Override
    protected void onDisable() {
        MinecraftClient.getInstance().options.getGamma().setValue(previousGamma);
    }
}
