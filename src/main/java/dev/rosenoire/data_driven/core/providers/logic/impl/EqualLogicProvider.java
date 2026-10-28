package dev.rosenoire.data_driven.core.providers.logic.impl;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.core.providers.logic.SimpleComparisonLogicProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.shared.index.AllLogicProviders;
import org.jspecify.annotations.NonNull;

public class EqualLogicProvider extends SimpleComparisonLogicProvider {
    public static final @NonNull MapCodec<EqualLogicProvider> CODEC = LogicProvider
            .createABCodec(EqualLogicProvider::a, EqualLogicProvider::b, EqualLogicProvider::new);

    public EqualLogicProvider(@NonNull NumericalProvider a, @NonNull NumericalProvider b) {
        super(a, b);
    }

    @Override
    public @NonNull LogicProviderType<?> getType() {
        return AllLogicProviders.EQUAL;
    }

    @Override
    protected boolean compare(int comparisonOutput) {
        return comparisonOutput == 0;
    }
}
