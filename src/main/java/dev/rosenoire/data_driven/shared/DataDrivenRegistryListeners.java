package dev.rosenoire.data_driven.shared;

import dev.rosenoire.data_driven.core.registry_listeners.RegistryReloadListenerFactory;
import dev.rosenoire.data_driven.core.registry_listeners.RegistryReloadListeners;
import org.jspecify.annotations.NonNull;

public class DataDrivenRegistryListeners {
    static void add(@NonNull RegistryReloadListenerFactory<?> factory) {
        RegistryReloadListeners.FACTORIES.add(factory);
    }
}
