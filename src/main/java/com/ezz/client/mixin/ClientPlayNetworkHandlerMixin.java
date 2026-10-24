package com.ezz.client.mixin;

import com.ezz.client.EzzClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void ezz$handleChatCommand(String message, CallbackInfo ci) {
        if (message != null && message.startsWith(".ezz")) {
            ci.cancel();
            EzzClient.handleChatCommand(message);
        }
    }
}
