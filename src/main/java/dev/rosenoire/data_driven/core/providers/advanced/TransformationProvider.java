package dev.rosenoire.data_driven.core.providers.advanced;

import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.ValueProvider;
import org.jspecify.annotations.NonNull;

public record TransformationProvider(@NonNull Vector3fProvider translate,
                                     @NonNull Vector3fProvider scale,
                                     @NonNull QuaternionfProvider leftRotation,
                                     @NonNull QuaternionfProvider rightRotation)
        implements ValueProvider<Transformation> {

    public static final @NonNull MapCodec<TransformationProvider> CODEC = MapCodecBuilder
            .<TransformationProvider>builder()
            .optional("translate", Vector3fProvider.CODEC, TransformationProvider::translate, Vector3fProvider.IDENTITY)
            .optional("scale", Vector3fProvider.CODEC, TransformationProvider::scale, Vector3fProvider.IDENTITY)
            .optional("left_rotation", QuaternionfProvider.CODEC, TransformationProvider::leftRotation, QuaternionfProvider.IDENTITY)
            .optional("right_rotation", QuaternionfProvider.CODEC, TransformationProvider::rightRotation, QuaternionfProvider.IDENTITY)
            .build(TransformationProvider::new);

    @Override
    public @NonNull Transformation compute(@NonNull ProviderContext context) {
        return new Transformation(
                this.translate().compute(context),
                this.leftRotation().compute(context),
                this.scale().compute(context),
                this.rightRotation().compute(context)
        );
    }
}
