package dev.rosenoire.data_driven.core.providers.numerical;

import com.mojang.serialization.MapCodec;
import org.jspecify.annotations.NonNull;

public record NumericalProviderType<T extends NumericalProvider>(@NonNull MapCodec<T> mapCodec) {
}
