package dev.rosenoire.data_driven.core.registry_listeners;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public abstract class SimpleRegistryReloadListener implements PreparableReloadListener {
    protected final ReloadableServerRegistries.@NonNull Holder fullRegistryHolder;

    public SimpleRegistryReloadListener(ReloadableServerRegistries.@NonNull Holder fullRegistryHolder) {
        this.fullRegistryHolder = fullRegistryHolder;
    }

    protected abstract @NonNull FileToIdConverter getLister();

    protected abstract void acceptAsync(@NonNull Map<Identifier, Resource> files);

    @Override
    public @NonNull CompletableFuture<Void> reload(@NonNull SharedState currentReload,
                                                   @NonNull Executor taskExecutor,
                                                   @NonNull PreparationBarrier preparationBarrier,
                                                   @NonNull Executor reloadExecutor) {
        var manager = currentReload.resourceManager();
        return CompletableFuture
                .supplyAsync(() -> this.getLister().listMatchingResources(manager), taskExecutor)
                .thenCompose(preparationBarrier::wait) // Wait to make sure everything is loaded.
                .thenAcceptAsync(this::acceptAsync, reloadExecutor);
    }

    protected static <T> @NonNull RegistryOps<JsonElement> createJsonOps(
            @NonNull FabricBootstrapContext<T> lookup
    ) {
        return RegistryOps.create(
                JsonOps.INSTANCE,
                new RegistryOps.RegistryInfoLookup() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public <T> @NonNull Optional<HolderGetter<T>> lookup(
                            @NonNull ResourceKey<? extends Registry<? extends T>> registryKey
                    ) {
                        return Optional.of((HolderGetter<T>) lookup.getEntryLookup());
                    }
                }
        );
    }
}
