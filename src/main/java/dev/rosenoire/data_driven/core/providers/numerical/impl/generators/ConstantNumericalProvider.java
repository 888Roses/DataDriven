package dev.rosenoire.data_driven.core.providers.numerical.impl.generators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public record ConstantNumericalProvider(double value) implements NumericalProvider {
    public static final @NonNull MapCodec<ConstantNumericalProvider> CODEC = SimpleCodecs.mapCodec(
            "value", Codec.DOUBLE, ConstantNumericalProvider::value,
            ConstantNumericalProvider::new
    );

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.CONSTANT;
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        return this.value();
    }
}
