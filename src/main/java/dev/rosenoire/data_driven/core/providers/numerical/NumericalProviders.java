package dev.rosenoire.data_driven.core.providers.numerical;

import dev.rosenoire.data_driven.core.providers.numerical.impl.generators.ConstantNumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.impl.generators.UniformNumericalProvider;
import org.jspecify.annotations.NonNull;

public final class NumericalProviders {
    private NumericalProviders() {
    }

    public static @NonNull ConstantNumericalProvider constant(double value) {
        return new ConstantNumericalProvider(value);
    }

    public static @NonNull UniformNumericalProvider uniform(@NonNull NumericalProvider min,
                                                            @NonNull NumericalProvider max) {
        return new UniformNumericalProvider(min, max);
    }

    public static @NonNull UniformNumericalProvider uniform(double min, double max) {
        return uniform(constant(min), constant(max));
    }
}
