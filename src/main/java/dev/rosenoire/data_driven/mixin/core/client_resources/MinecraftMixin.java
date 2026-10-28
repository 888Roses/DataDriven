package dev.rosenoire.data_driven.mixin.core.client_resources;

import dev.rosenoire.data_driven.client.index.AllClientReloadListeners;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    @Final
    private ReloadableResourceManager resourceManager;

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Gui;registerReloadListeners(Lnet/minecraft/server/packs/resources/ReloadableResourceManager;)V"
            )
    )
    private void init$initialize_custom_reload_listeners(@NonNull GameConfig gameConfig,
                                                         @NonNull CallbackInfo ci) {
        AllClientReloadListeners.register(this.resourceManager);
    }
}
