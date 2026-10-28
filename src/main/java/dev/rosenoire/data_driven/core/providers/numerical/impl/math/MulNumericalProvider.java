package dev.rosenoire.data_driven.core.providers.numerical.impl.math;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.BiDynamicInputNumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public class MulNumericalProvider extends BiDynamicInputNumericalProvider {
    public static final @NonNull MapCodec<MulNumericalProvider> CODEC = simpleCodec(MulNumericalProvider::new);

    public MulNumericalProvider(@NonNull NumericalProvider a, @NonNull NumericalProvider b) {
        super(a, b);
    }

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.MUL;
    }

    @Override
    protected double compute(@NonNull ProviderContext context, double a, double b) {
        return a * b;
    }
}
