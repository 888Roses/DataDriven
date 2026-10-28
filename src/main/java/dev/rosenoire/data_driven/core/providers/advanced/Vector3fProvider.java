package dev.rosenoire.data_driven.core.providers.advanced;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.ValueProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviders;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record Vector3fProvider
        (@NonNull NumericalProvider x, @NonNull NumericalProvider y, @NonNull NumericalProvider z)
        implements ValueProvider<Vector3f> {

    public static final @NonNull Vector3fProvider IDENTITY = new Vector3fProvider(
            NumericalProviders.constant(0),
            NumericalProviders.constant(0),
            NumericalProviders.constant(0)
    );

    public static final @NonNull Codec<Vector3fProvider> CODEC = NumericalProvider.CODEC
            .listOf()
            .xmap(
                    list -> {
                        if (list.size() == 1) return new Vector3fProvider(list.getFirst(), list.getFirst(), list.getFirst());
                        else if (list.size() == 3) return new Vector3fProvider(list.getFirst(), list.get(1), list.get(2));
                        else return IDENTITY;
                    },
                    element -> List.of(element.x, element.y, element.z)
            );

    @Override
    public @NonNull Vector3f compute(@NonNull ProviderContext context) {
        return new Vector3f(
                (float) (double) this.x.compute(context),
                (float) (double) this.y.compute(context),
                (float) (double) this.z.compute(context)
        );
    }
}
