package dev.rosenoire.data_driven.core.providers.numerical.impl.math;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public record LerpNumericalProvider
        (@NonNull NumericalProvider from, @NonNull NumericalProvider to, @NonNull NumericalProvider delta)
        implements NumericalProvider {

    public static final @NonNull MapCodec<LerpNumericalProvider> CODEC = SimpleCodecs.mapCodec(
            "from", NumericalProvider.CODEC, LerpNumericalProvider::from,
            "to", NumericalProvider.CODEC, LerpNumericalProvider::to,
            "delta", NumericalProvider.CODEC, LerpNumericalProvider::delta,
            LerpNumericalProvider::new
    );

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.LERP;
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        var delta = this.delta().compute(context);
        var from = this.from().compute(context);
        var to = this.to().compute(context);
        return Mth.lerp(delta, from, to);
    }
}
