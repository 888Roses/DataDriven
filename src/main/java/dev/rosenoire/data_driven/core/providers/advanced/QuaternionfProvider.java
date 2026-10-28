package dev.rosenoire.data_driven.core.providers.advanced;

import com.mojang.serialization.Codec;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.ValueProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviders;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record QuaternionfProvider
        (@NonNull NumericalProvider x, @NonNull NumericalProvider y, @NonNull NumericalProvider z, @NonNull NumericalProvider w)
        implements ValueProvider<Quaternionf> {

    public static final @NonNull QuaternionfProvider IDENTITY = new QuaternionfProvider(
            NumericalProviders.constant(0),
            NumericalProviders.constant(0),
            NumericalProviders.constant(0),
            NumericalProviders.constant(1)
    );

    public static final @NonNull Codec<QuaternionfProvider> CODEC = NumericalProvider.CODEC
            .listOf()
            .xmap(
                    list -> {
                        if (list.size() != 4) return IDENTITY;
                        else return new QuaternionfProvider(list.getFirst(), list.get(1), list.get(2), list.get(3));
                    },
                    element -> List.of(element.x, element.y, element.z, element.w)
            );

    @Override
    public @NonNull Quaternionf compute(@NonNull ProviderContext context) {
        return new Quaternionf(
                (float) (double) this.x.compute(context),
                (float) (double) this.y.compute(context),
                (float) (double) this.z.compute(context),
                (float) (double) this.w.compute(context)
        );
    }
}
