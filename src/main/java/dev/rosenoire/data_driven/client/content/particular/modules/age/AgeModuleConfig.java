package dev.rosenoire.data_driven.client.content.particular.modules.age;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.index.AllParticularModuleTypes;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviders;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviders;
import org.jspecify.annotations.NonNull;

public record AgeModuleConfig(
        @NonNull NumericalProvider lifetime,
        @NonNull NumericalProvider speed,
        @NonNull LogicProvider shouldAge
) implements ParticularModuleConfig {

    public static final @NonNull MapCodec<AgeModuleConfig> MAP_CODEC = MapCodecBuilder
            .<AgeModuleConfig>builder()
            .required("lifetime", NumericalProvider.CODEC, AgeModuleConfig::lifetime)
            .optional("speed", NumericalProvider.CODEC, AgeModuleConfig::speed, NumericalProviders.constant(1f))
            .optional("should_age", LogicProvider.CODEC, AgeModuleConfig::shouldAge, LogicProviders.constant(true))
            .build(AgeModuleConfig::new);

    @Override
    public @NonNull ParticularModuleConfigType<?> getType() {
        return AllParticularModuleTypes.AGE;
    }
}
