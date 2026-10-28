package dev.rosenoire.data_driven.core.registry_listeners;

import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.jspecify.annotations.NonNull;

@FunctionalInterface
public interface RegistryReloadListenerFactory<L extends PreparableReloadListener> {
    @NonNull L create(ReloadableServerRegistries.@NonNull Holder fullRegistryHolder);
}
