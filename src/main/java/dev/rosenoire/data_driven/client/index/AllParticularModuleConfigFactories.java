package dev.rosenoire.data_driven.client.index;

import dev.rosenoire.data_driven.client.content.particular.modules.age.AgeModule;
import dev.rosenoire.data_driven.client.content.particular.modules.age.AgeModuleConfig;
import dev.rosenoire.data_driven.client.content.particular.modules.renderer.RendererModule;
import dev.rosenoire.data_driven.client.content.particular.modules.renderer.RendererModuleConfig;
import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularManager;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

import java.util.function.BiFunction;

@ApiStatus.Internal
@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface AllParticularModuleConfigFactories {
    static void initialize() {
        registerConfigFactory(AgeModuleConfig.class, AgeModule::new);
        registerConfigFactory(RendererModuleConfig.class, RendererModule::new);
    }

    @SuppressWarnings("unchecked")
    private static <C extends ParticularModuleConfig, M extends ParticularModule<C>> void registerConfigFactory(
            @NonNull Class<C> configType,
            @NonNull BiFunction<Particular, C, M> factory
    ) {
        ParticularManager.registerModuleConfigFactory(
                configType,
                (particular, config) -> factory.apply(particular, (C) config)
        );
    }
}
