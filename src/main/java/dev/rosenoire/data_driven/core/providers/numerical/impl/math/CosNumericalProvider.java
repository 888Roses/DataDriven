package dev.rosenoire.data_driven.core.providers.numerical.impl.math;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.DynamicInputNumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public class CosNumericalProvider extends DynamicInputNumericalProvider {
    public static final @NonNull MapCodec<CosNumericalProvider> CODEC = simpleCodec(CosNumericalProvider::new);

    public CosNumericalProvider(@NonNull NumericalProvider value) {
        super(value);
    }

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.COS;
    }

    @Override
    protected double compute(@NonNull ProviderContext context, double value) {
        return Math.cos(value);
    }
}
