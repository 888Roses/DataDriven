package dev.rosenoire.data_driven.core.particular.storage.modules;

import com.mojang.serialization.Codec;
import dev.rosenoire.data_driven.core.AllRegistries;
import org.jspecify.annotations.NonNull;

public interface ParticularModuleConfig {
    Codec<ParticularModuleConfig> MODULE_CODEC = AllRegistries.PARTICULAR_MODULE_CONFIG_TYPES
            .byNameCodec()
            .dispatch("type", ParticularModuleConfig::getType, ParticularModuleConfigType::codec);

    @NonNull ParticularModuleConfigType<?> getType();
}
