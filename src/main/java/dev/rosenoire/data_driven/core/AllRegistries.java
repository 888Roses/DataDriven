package dev.rosenoire.data_driven.core;

import com.mojang.serialization.Lifecycle;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.DataDriven;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

@ApiStatus.NonExtendable
public interface AllRegistries {
    Registry<LogicProviderType<?>> LOGIC_PROVIDERS = registry("logic_providers");
    Registry<NumericalProviderType<?>> NUMERICAL_PROVIDERS = registry("numerical_providers");
    Registry<ParticularModuleConfigType<?>> PARTICULAR_MODULE_CONFIG_TYPES = registry("particular_module_config_types");

    private static <T> @NonNull MappedRegistry<T> registry(@NonNull String modPath) {
        return registry(modPath, Lifecycle.stable());
    }

    private static <T> @NonNull MappedRegistry<T> registry(@NonNull String modPath, @NonNull Lifecycle lifecycle) {
        return new MappedRegistry<>(
                ResourceKey.createRegistryKey(DataDriven.id(modPath)),
                lifecycle
        );
    }
}
