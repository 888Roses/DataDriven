package dev.rosenoire.data_driven.core.providers.numerical.impl.conditional;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public record IfNumericalProvider
        (@NonNull LogicProvider condition, @NonNull NumericalProvider ifTrue, @NonNull NumericalProvider ifFalse)
        implements NumericalProvider {

    public static final @NonNull MapCodec<IfNumericalProvider> CODEC = SimpleCodecs.mapCodec(
            "condition", LogicProvider.CODEC, IfNumericalProvider::condition,
            "if_true", NumericalProvider.CODEC, IfNumericalProvider::ifTrue,
            "if_false", NumericalProvider.CODEC, IfNumericalProvider::ifFalse,
            IfNumericalProvider::new
    );

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.IF;
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        if (this.condition().compute(context)) {
            return this.ifTrue().compute(context);
        } else {
            return this.ifFalse().compute(context);
        }
    }
}
