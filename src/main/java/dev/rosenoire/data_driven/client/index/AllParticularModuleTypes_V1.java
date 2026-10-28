package dev.rosenoire.data_driven.client.index;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.content.particular._modules.aging.AgingModule;
import dev.rosenoire.data_driven.client.content.particular._modules.aging.AgingModuleConfig;
import dev.rosenoire.data_driven.client.content.particular._modules.gravity.GravityModule;
import dev.rosenoire.data_driven.client.content.particular._modules.gravity.GravityModuleConfig;
import dev.rosenoire.data_driven.client.content.particular._modules.renderer.RendererModule;
import dev.rosenoire.data_driven.client.content.particular._modules.renderer.RendererModuleConfig;
import dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.RendererModule2;
import dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.RendererModuleConfig2;
import dev.rosenoire.data_driven.core.AllRegistries;
import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularManager;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

import java.util.function.BiFunction;

@Deprecated
@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface AllParticularModuleTypes_V1 {
    ParticularModuleConfigType<GravityModuleConfig> GRAVITY = module("gravity", GravityModuleConfig.CODEC);
    ParticularModuleConfigType<AgingModuleConfig> AGING = module("aging", AgingModuleConfig.CODEC);
    ParticularModuleConfigType<RendererModuleConfig> RENDERER = module("renderer", RendererModuleConfig.CODEC);

    // V2
    ParticularModuleConfigType<RendererModuleConfig2> RENDERER2 = module("renderer2", RendererModuleConfig2.CODEC);

    static void initialize() {
        configFactory(GravityModuleConfig.class, GravityModule::new);
        configFactory(RendererModuleConfig.class, RendererModule::new);
        configFactory(RendererModuleConfig2.class, RendererModule2::new);
        configFactory(AgingModuleConfig.class, AgingModule::new);
    }

    private static <C extends ParticularModuleConfig, M extends ParticularModule<C>> void configFactory(
            @NonNull Class<C> clazz,
            @NonNull BiFunction<Particular, C, M> factory
    ) {
        ParticularManager.registerModuleConfigFactory(clazz, (particular, config) -> {
            //noinspection unchecked
            return factory.apply(particular, (C) config);
        });
    }

    private static <M extends ParticularModuleConfig> @NonNull ParticularModuleConfigType<M> module(
            @NonNull String name,
            @NonNull MapCodec<M> mapCodec
    ) {
        return Registry.register(
                AllRegistries.PARTICULAR_MODULE_CONFIG_TYPES,
                Identifier.withDefaultNamespace(name),
                new ParticularModuleConfigType<>(mapCodec)
        );
    }
}
