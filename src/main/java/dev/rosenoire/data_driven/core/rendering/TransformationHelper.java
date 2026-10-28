package dev.rosenoire.data_driven.core.rendering;

import com.mojang.math.Transformation;
import com.mojang.serialization.Codec;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;

import java.util.List;

public final class TransformationHelper {
    private TransformationHelper() {
    }

    public static final @NonNull Codec<Transformation> CODEC = MapCodecBuilder
            .<Transformation>builder()
            .optional("translate", ExtraCodecs.VECTOR3F, Transformation::translation, new Vector3f())
            .optional("scale", ExtraCodecs.VECTOR3F, Transformation::scale, new Vector3f(1, 1, 1))
            .optional("left_rotation", ExtraCodecs.QUATERNIONF, Transformation::leftRotation, new Quaternionf().identity())
            .optional("right_rotation", ExtraCodecs.QUATERNIONF, Transformation::rightRotation, new Quaternionf().identity())
            .build((translate, scale, left_rotation, right_rotation) -> new Transformation(
                    translate, left_rotation, scale, right_rotation
            ))
            .codec();

    public static @NonNull Transformation combine(@NonNull List<Transformation> transformations) {
        if (transformations.size() < 2) {
            if (transformations.isEmpty()) return Transformation.IDENTITY;
            return transformations.getFirst();
        }

        var transformation = transformations.removeFirst();
        for (var trans : transformations) transformation = transformation.compose(trans);
        return transformation;
    }
}
