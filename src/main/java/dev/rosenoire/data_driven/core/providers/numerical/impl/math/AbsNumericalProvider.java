package dev.rosenoire.data_driven.core.providers.numerical.impl.math;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.DynamicInputNumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public class AbsNumericalProvider extends DynamicInputNumericalProvider {
    public static final @NonNull MapCodec<AbsNumericalProvider> CODEC = simpleCodec(AbsNumericalProvider::new);

    public AbsNumericalProvider(@NonNull NumericalProvider value) {
        super(value);
    }

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.ABS;
    }

    @Override
    protected double compute(@NonNull ProviderContext context, double value) {
        return Math.abs(value);
    }
}
