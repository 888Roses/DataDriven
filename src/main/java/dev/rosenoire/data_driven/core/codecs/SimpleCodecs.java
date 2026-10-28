package dev.rosenoire.data_driven.core.codecs;

import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Function4;
import com.mojang.datafixers.util.Function5;
import com.mojang.datafixers.util.Function6;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class SimpleCodecs {
    private SimpleCodecs() {
    }

    public static <T> @NonNull MapCodec<T> mapCodec(@NonNull Supplier<T> constructor) {
        return MapCodec.unit(constructor);
    }

    public static <T, A1> @NonNull MapCodec<T> mapCodec(
            @NonNull String name1, @NonNull Codec<A1> codec1, @NonNull Function<T, A1> getter1,
            @NonNull Function<A1, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(codec1.fieldOf(name1).forGetter(getter1))
                .apply(instance, constructor)
        );
    }

    public static <T, A1, A2> @NonNull MapCodec<T> mapCodec(
            @NonNull String name1, @NonNull Codec<A1> codec1, @NonNull Function<T, A1> getter1,
            @NonNull String name2, @NonNull Codec<A2> codec2, @NonNull Function<T, A2> getter2,
            @NonNull BiFunction<A1, A2, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        codec1.fieldOf(name1).forGetter(getter1),
                        codec2.fieldOf(name2).forGetter(getter2)
                )
                .apply(instance, constructor)
        );
    }

    public static <T, A1, A2, A3> @NonNull MapCodec<T> mapCodec(
            @NonNull String name1, @NonNull Codec<A1> codec1, @NonNull Function<T, A1> getter1,
            @NonNull String name2, @NonNull Codec<A2> codec2, @NonNull Function<T, A2> getter2,
            @NonNull String name3, @NonNull Codec<A3> codec3, @NonNull Function<T, A3> getter3,
            @NonNull Function3<A1, A2, A3, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        codec1.fieldOf(name1).forGetter(getter1),
                        codec2.fieldOf(name2).forGetter(getter2),
                        codec3.fieldOf(name3).forGetter(getter3)
                )
                .apply(instance, constructor)
        );
    }

    public static <T, A1, A2, A3, A4> @NonNull MapCodec<T> mapCodec(
            @NonNull String name1, @NonNull Codec<A1> codec1, @NonNull Function<T, A1> getter1,
            @NonNull String name2, @NonNull Codec<A2> codec2, @NonNull Function<T, A2> getter2,
            @NonNull String name3, @NonNull Codec<A3> codec3, @NonNull Function<T, A3> getter3,
            @NonNull String name4, @NonNull Codec<A4> codec4, @NonNull Function<T, A4> getter4,
            @NonNull Function4<A1, A2, A3, A4, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        codec1.fieldOf(name1).forGetter(getter1),
                        codec2.fieldOf(name2).forGetter(getter2),
                        codec3.fieldOf(name3).forGetter(getter3),
                        codec4.fieldOf(name4).forGetter(getter4)
                )
                .apply(instance, constructor)
        );
    }

    public static <T, A1, A2, A3, A4, A5> @NonNull MapCodec<T> mapCodec(
            @NonNull String name1, @NonNull Codec<A1> codec1, @NonNull Function<T, A1> getter1,
            @NonNull String name2, @NonNull Codec<A2> codec2, @NonNull Function<T, A2> getter2,
            @NonNull String name3, @NonNull Codec<A3> codec3, @NonNull Function<T, A3> getter3,
            @NonNull String name4, @NonNull Codec<A4> codec4, @NonNull Function<T, A4> getter4,
            @NonNull String name5, @NonNull Codec<A5> codec5, @NonNull Function<T, A5> getter5,
            @NonNull Function5<A1, A2, A3, A4, A5, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        codec1.fieldOf(name1).forGetter(getter1),
                        codec2.fieldOf(name2).forGetter(getter2),
                        codec3.fieldOf(name3).forGetter(getter3),
                        codec4.fieldOf(name4).forGetter(getter4),
                        codec5.fieldOf(name5).forGetter(getter5)
                )
                .apply(instance, constructor)
        );
    }

    public static <T, A1, A2, A3, A4, A5, A6> @NonNull MapCodec<T> mapCodec(
            @NonNull String name1, @NonNull Codec<A1> codec1, @NonNull Function<T, A1> getter1,
            @NonNull String name2, @NonNull Codec<A2> codec2, @NonNull Function<T, A2> getter2,
            @NonNull String name3, @NonNull Codec<A3> codec3, @NonNull Function<T, A3> getter3,
            @NonNull String name4, @NonNull Codec<A4> codec4, @NonNull Function<T, A4> getter4,
            @NonNull String name5, @NonNull Codec<A5> codec5, @NonNull Function<T, A5> getter5,
            @NonNull String name6, @NonNull Codec<A6> codec6, @NonNull Function<T, A6> getter6,
            @NonNull Function6<A1, A2, A3, A4, A5, A6, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        codec1.fieldOf(name1).forGetter(getter1),
                        codec2.fieldOf(name2).forGetter(getter2),
                        codec3.fieldOf(name3).forGetter(getter3),
                        codec4.fieldOf(name4).forGetter(getter4),
                        codec5.fieldOf(name5).forGetter(getter5),
                        codec6.fieldOf(name6).forGetter(getter6)
                )
                .apply(instance, constructor)
        );
    }
}
