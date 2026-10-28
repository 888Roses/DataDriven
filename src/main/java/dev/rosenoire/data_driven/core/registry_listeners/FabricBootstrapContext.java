package dev.rosenoire.data_driven.core.registry_listeners;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BootstrapRegistry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FabricBootstrapContext<T> implements BootstrapContext<T> {
    private final HolderLookup.Provider registries;
    private final ResourceKey<? extends Registry<T>> registryKey;
    private final Map<ResourceKey<T>, T> entries = new LinkedHashMap<>();
    private final BootstrapRegistry<T> entryLookup;

    public FabricBootstrapContext(HolderLookup.Provider registries, ResourceKey<? extends Registry<T>> registryKey) {
        this.registries = registries;
        this.registryKey = registryKey;
        this.entryLookup = new BootstrapRegistry<>(registryKey, Lifecycle.stable());
    }

    @Override
    public Holder.@NonNull Reference<T> register(@NonNull ResourceKey<T> key,
                                                 @NonNull T value) {
        if (this.entries.putIfAbsent(key, value) != null) {
            throw new IllegalStateException("Duplicate registration for " + key);
        }

        return this.entryLookup.getOrThrow(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S> @NonNull HolderGetter<S> lookup(ResourceKey<? extends Registry<? extends S>> key) {
        if (key.equals(registryKey)) {
            return (HolderGetter<S>) entryLookup;
        }

        return registries.lookupOrThrow(key);
    }

    @Override
    public <S> @NonNull Stream<Holder.Reference<S>> listContextElements(
            @NonNull ResourceKey<? extends Registry<? extends S>> key
    ) {
        return this.registries
                .lookupOrThrow(key)
                .listElements();
    }

    public @NonNull Map<ResourceKey<T>, T> getEntries() {
        return this.entries;
    }

    public @NonNull BootstrapRegistry<T> getEntryLookup() {
        return this.entryLookup;
    }
}
