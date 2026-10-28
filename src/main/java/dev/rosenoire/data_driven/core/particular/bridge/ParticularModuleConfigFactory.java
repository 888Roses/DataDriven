package dev.rosenoire.data_driven.core.particular.bridge;

import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import org.jspecify.annotations.NonNull;

public interface ParticularModuleConfigFactory<C extends ParticularModuleConfig> {
    @NonNull ParticularModule<C> create(@NonNull Particular particular, @NonNull ParticularModuleConfig config);
}
