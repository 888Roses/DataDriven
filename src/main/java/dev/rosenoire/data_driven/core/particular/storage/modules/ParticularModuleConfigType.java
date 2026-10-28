package dev.rosenoire.data_driven.core.particular.storage.modules;

import com.mojang.serialization.MapCodec;
import org.jspecify.annotations.NonNull;

public record ParticularModuleConfigType<M extends ParticularModuleConfig>(@NonNull MapCodec<M> codec) {
}
