package dev.rosenoire.data_driven.client.content.particular._modules.gravity;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.index.AllParticularModuleTypes_V1;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import org.jspecify.annotations.NonNull;

public record GravityModuleConfig
        (@NonNull NumericalProvider gravity)
        implements ParticularModuleConfig {

    public static final @NonNull MapCodec<GravityModuleConfig> CODEC = MapCodecBuilder
            .<GravityModuleConfig>builder()
            .required("gravity", NumericalProvider.CODEC, GravityModuleConfig::gravity)
            .build(GravityModuleConfig::new);

    @Override
    public @NonNull ParticularModuleConfigType<?> getType() {
        return AllParticularModuleTypes_V1.GRAVITY;
    }
}
