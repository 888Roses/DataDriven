package dev.rosenoire.data_driven.core.providers.logic.impl;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.core.providers.logic.SimpleComparisonLogicProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.shared.index.AllLogicProviders;
import org.jspecify.annotations.NonNull;

public class LesserLogicProvider extends SimpleComparisonLogicProvider {
    public static final @NonNull MapCodec<LesserLogicProvider> CODEC = LogicProvider
            .createABCodec(LesserLogicProvider::a, LesserLogicProvider::b, LesserLogicProvider::new);

    public LesserLogicProvider(@NonNull NumericalProvider a, @NonNull NumericalProvider b) {
        super(a, b);
    }

    @Override
    public @NonNull LogicProviderType<?> getType() {
        return AllLogicProviders.LESSER;
    }

    @Override
    protected boolean compare(int comparisonOutput) {
        return comparisonOutput < 0;
    }
}
