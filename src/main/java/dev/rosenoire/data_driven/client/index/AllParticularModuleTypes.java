package dev.rosenoire.data_driven.client.index;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.content.particular.modules.age.AgeModuleConfig;
import dev.rosenoire.data_driven.client.content.particular.modules.renderer.RendererModuleConfig;
import dev.rosenoire.data_driven.core.AllRegistries;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.shared.DataDriven;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface AllParticularModuleTypes {
    ParticularModuleConfigType<AgeModuleConfig> AGE = registerVanilla("age", AgeModuleConfig.MAP_CODEC);
    ParticularModuleConfigType<RendererModuleConfig> RENDERER = registerVanilla("renderer", RendererModuleConfig.MAP_CODEC);

    static void initialize() {
    }

    static <C extends ParticularModuleConfig> @NonNull ParticularModuleConfigType<C> register(
            @NonNull Identifier identifier,
            @NonNull MapCodec<C> mapCodec
    ) {
        return Registry.register(
                AllRegistries.PARTICULAR_MODULE_CONFIG_TYPES,
                identifier,
                new ParticularModuleConfigType<>(mapCodec)
        );
    }

    static <C extends ParticularModuleConfig> @NonNull ParticularModuleConfigType<C> registerVanilla(
            @NonNull String name,
            @NonNull MapCodec<C> mapCodec
    ) {
        return register(Identifier.withDefaultNamespace(name), mapCodec);
    }

    static <C extends ParticularModuleConfig> @NonNull ParticularModuleConfigType<C> registerDataDriven(
            @NonNull String name,
            @NonNull MapCodec<C> mapCodec
    ) {
        return register(DataDriven.id(name), mapCodec);
    }
}
