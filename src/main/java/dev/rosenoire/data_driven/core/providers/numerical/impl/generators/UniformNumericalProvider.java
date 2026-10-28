package dev.rosenoire.data_driven.core.providers.numerical.impl.generators;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public record UniformNumericalProvider
        (@NonNull NumericalProvider min, @NonNull NumericalProvider max)
        implements NumericalProvider {

    public static final @NonNull MapCodec<UniformNumericalProvider> CODEC = SimpleCodecs.mapCodec(
            "min", NumericalProvider.CODEC, UniformNumericalProvider::min,
            "max", NumericalProvider.CODEC, UniformNumericalProvider::max,
            UniformNumericalProvider::new
    );

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.UNIFORM;
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        return context.uniform(this.min().compute(context), this.max().compute(context));
    }
}
