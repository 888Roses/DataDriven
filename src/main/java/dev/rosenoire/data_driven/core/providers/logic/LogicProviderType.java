package dev.rosenoire.data_driven.core.providers.logic;

import com.mojang.serialization.MapCodec;
import org.jspecify.annotations.NonNull;

public record LogicProviderType<T extends LogicProvider>(@NonNull MapCodec<T> mapCodec) {
}
