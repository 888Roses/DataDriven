package dev.rosenoire.data_driven.core.providers.logic.impl;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.core.providers.logic.SimpleComparisonLogicProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.shared.index.AllLogicProviders;
import org.jspecify.annotations.NonNull;

public class GreaterEqualLogicProvider extends SimpleComparisonLogicProvider {
    public static final @NonNull MapCodec<GreaterEqualLogicProvider> CODEC = LogicProvider
            .createABCodec(GreaterEqualLogicProvider::a, GreaterEqualLogicProvider::b, GreaterEqualLogicProvider::new);

    public GreaterEqualLogicProvider(@NonNull NumericalProvider a, @NonNull NumericalProvider b) {
        super(a, b);
    }

    @Override
    public @NonNull LogicProviderType<?> getType() {
        return AllLogicProviders.GREATER_EQUAL;
    }

    @Override
    protected boolean compare(int comparisonOutput) {
        return comparisonOutput >= 0;
    }
}
