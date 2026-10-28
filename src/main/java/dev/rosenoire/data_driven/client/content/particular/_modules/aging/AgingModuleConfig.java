package dev.rosenoire.data_driven.client.content.particular._modules.aging;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.index.AllParticularModuleTypes_V1;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviders;
import org.jspecify.annotations.NonNull;

public record AgingModuleConfig
        (@NonNull NumericalProvider lifetime, @NonNull NumericalProvider agingSpeed)
        implements ParticularModuleConfig {

    public static final @NonNull MapCodec<AgingModuleConfig> CODEC = MapCodecBuilder
            .<AgingModuleConfig>builder()
            .required("lifetime", NumericalProvider.CODEC, AgingModuleConfig::lifetime)
            .optional("aging_speed", NumericalProvider.CODEC, AgingModuleConfig::agingSpeed, NumericalProviders.constant(1))
            .build(AgingModuleConfig::new);

    @Override
    public @NonNull ParticularModuleConfigType<?> getType() {
        return AllParticularModuleTypes_V1.AGING;
    }
}
