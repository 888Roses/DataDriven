package dev.rosenoire.data_driven.core.registry_listeners;

import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface RegistryReloadListeners {
    List<RegistryReloadListenerFactory<?>> FACTORIES = FactoryListBuilder
            .create()
            .build();

    class FactoryListBuilder {
        private final @NonNull List<RegistryReloadListenerFactory<?>> factories = new ArrayList<>();

        public static FactoryListBuilder create() {
            return new FactoryListBuilder();
        }

        public @NonNull FactoryListBuilder add(@NonNull RegistryReloadListenerFactory<?> factory) {
            this.factories.add(factory);
            return this;
        }

        public <L extends PreparableReloadListener> @NonNull FactoryListBuilder add(@NonNull Supplier<L> factory) {
            this.factories.add(_ -> factory.get());
            return this;
        }

        public @NonNull List<RegistryReloadListenerFactory<?>> build() {
            return List.copyOf(this.factories);
        }
    }
}
