package dev.rosenoire.data_driven.core.providers.numerical.impl.generators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviders;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import org.jspecify.annotations.NonNull;

public record VarNumericalProvider
        (@NonNull String name, @NonNull NumericalProvider fallback) // Fallback is optional
        implements NumericalProvider {

    public static final @NonNull MapCodec<VarNumericalProvider> CODEC = MapCodecBuilder
            .<VarNumericalProvider>builder()
            .required("name", Codec.STRING, VarNumericalProvider::name)
            .optional("fallback", NumericalProvider.CODEC, VarNumericalProvider::fallback, NumericalProviders.constant(0f))
            .build(VarNumericalProvider::new);

    @Override
    public @NonNull NumericalProviderType<?> getType() {
        return AllNumericalProviders.VAR;
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        return context.getDoubleOr(this.name, this.fallback.compute(context));
    }
}
