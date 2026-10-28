package dev.rosenoire.data_driven.core.providers.numerical.impl.math;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record SumNumericalProvider(@NonNull List<NumericalProvider> values) implements NumericalProvider {
    public static final @NonNull MapCodec<SumNumericalProvider> CODEC = SimpleCodecs.mapCodec(
            "values", NumericalProvider.CODEC.listOf(), SumNumericalProvider::values,
            SumNumericalProvider::new
    );

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.SUM;
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        var value = 0d;
        for (var child : this.values()) value += child.compute(context);
        return value;
    }
}
