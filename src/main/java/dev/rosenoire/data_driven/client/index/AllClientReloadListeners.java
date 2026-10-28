package dev.rosenoire.data_driven.client.index;

import dev.rosenoire.data_driven.core.particular.storage.ParticularConfigRegistryReloadListener;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

@ApiStatus.NonExtendable
public interface AllClientReloadListeners {
    static void register(@NonNull ReloadableResourceManager resourceManager) {
        resourceManager.registerReloadListener(new ParticularConfigRegistryReloadListener());
    }
}
