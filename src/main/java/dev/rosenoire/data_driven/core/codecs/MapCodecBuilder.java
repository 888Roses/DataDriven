package dev.rosenoire.data_driven.core.codecs;

import com.mojang.datafixers.util.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.rosenoire.data_driven.core.codecs.fields.OptionalSerializableField;
import dev.rosenoire.data_driven.core.codecs.fields.SerializableField;
import dev.rosenoire.data_driven.core.codecs.fields.SimpleSerializableField;
import org.jspecify.annotations.NonNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class MapCodecBuilder {
    public static <P> @NonNull B0<P> builder() {
        return new B0<>();
    }

    public record B0<P>() {
        public @NonNull MapCodec<P> build(@NonNull Supplier<P> constructor) {
            return MapCodec.unit(constructor);
        }

        public <A> @NonNull B1<P, A> add(@NonNull SerializableField<P, A> field) {
            return new B1<>(field);
        }

        public <A> @NonNull B1<P, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B1<P, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B1<P, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }
    }

    public record B1<P, A1>(
            @NonNull SerializableField<P, A1> field1
    ) {
        public <A> @NonNull B2<P, A1, A> add(@NonNull SerializableField<P, A> field) {
            return new B2<>(this.field1, field);
        }

        public <A> @NonNull B2<P, A1, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B2<P, A1, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B2<P, A1, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function<A1, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B2<P, A1, A2>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2
    ) {
        public <A> @NonNull B3<P, A1, A2, A> add(@NonNull SerializableField<P, A> field) {
            return new B3<>(this.field1, this.field2, field);
        }

        public <A> @NonNull B3<P, A1, A2, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B3<P, A1, A2, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B3<P, A1, A2, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull BiFunction<A1, A2, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B3<P, A1, A2, A3>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3
    ) {
        public <A> @NonNull B4<P, A1, A2, A3, A> add(@NonNull SerializableField<P, A> field) {
            return new B4<>(this.field1, this.field2, this.field3, field);
        }

        public <A> @NonNull B4<P, A1, A2, A3, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B4<P, A1, A2, A3, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B4<P, A1, A2, A3, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function3<A1, A2, A3, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B4<P, A1, A2, A3, A4>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4
    ) {
        public <A> @NonNull B5<P, A1, A2, A3, A4, A> add(@NonNull SerializableField<P, A> field) {
            return new B5<>(this.field1, this.field2, this.field3, this.field4, field);
        }

        public <A> @NonNull B5<P, A1, A2, A3, A4, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B5<P, A1, A2, A3, A4, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B5<P, A1, A2, A3, A4, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function4<A1, A2, A3, A4, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B5<P, A1, A2, A3, A4, A5>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5
    ) {
        public <A> @NonNull B6<P, A1, A2, A3, A4, A5, A> add(@NonNull SerializableField<P, A> field) {
            return new B6<>(this.field1, this.field2, this.field3, this.field4, this.field5, field);
        }

        public <A> @NonNull B6<P, A1, A2, A3, A4, A5, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B6<P, A1, A2, A3, A4, A5, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B6<P, A1, A2, A3, A4, A5, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function5<A1, A2, A3, A4, A5, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B6<P, A1, A2, A3, A4, A5, A6>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6
    ) {
        public <A> @NonNull B7<P, A1, A2, A3, A4, A5, A6, A> add(@NonNull SerializableField<P, A> field) {
            return new B7<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, field);
        }

        public <A> @NonNull B7<P, A1, A2, A3, A4, A5, A6, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B7<P, A1, A2, A3, A4, A5, A6, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B7<P, A1, A2, A3, A4, A5, A6, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function6<A1, A2, A3, A4, A5, A6, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B7<P, A1, A2, A3, A4, A5, A6, A7>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6,
            @NonNull SerializableField<P, A7> field7
    ) {
        public <A> @NonNull B8<P, A1, A2, A3, A4, A5, A6, A7, A> add(@NonNull SerializableField<P, A> field) {
            return new B8<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, field);
        }

        public <A> @NonNull B8<P, A1, A2, A3, A4, A5, A6, A7, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B8<P, A1, A2, A3, A4, A5, A6, A7, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B8<P, A1, A2, A3, A4, A5, A6, A7, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function7<A1, A2, A3, A4, A5, A6, A7, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember(),
                            this.field7.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B8<P, A1, A2, A3, A4, A5, A6, A7, A8>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6,
            @NonNull SerializableField<P, A7> field7,
            @NonNull SerializableField<P, A8> field8
    ) {
        public <A> @NonNull B9<P, A1, A2, A3, A4, A5, A6, A7, A8, A> add(@NonNull SerializableField<P, A> field) {
            return new B9<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, field);
        }

        public <A> @NonNull B9<P, A1, A2, A3, A4, A5, A6, A7, A8, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B9<P, A1, A2, A3, A4, A5, A6, A7, A8, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B9<P, A1, A2, A3, A4, A5, A6, A7, A8, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function8<A1, A2, A3, A4, A5, A6, A7, A8, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember(),
                            this.field7.getSerializedMember(),
                            this.field8.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B9<P, A1, A2, A3, A4, A5, A6, A7, A8, A9>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6,
            @NonNull SerializableField<P, A7> field7,
            @NonNull SerializableField<P, A8> field8,
            @NonNull SerializableField<P, A9> field9
    ) {
        public <A> @NonNull B10<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A> add(@NonNull SerializableField<P, A> field) {
            return new B10<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, field);
        }

        public <A> @NonNull B10<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B10<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B10<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember(),
                            this.field7.getSerializedMember(),
                            this.field8.getSerializedMember(),
                            this.field9.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B10<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6,
            @NonNull SerializableField<P, A7> field7,
            @NonNull SerializableField<P, A8> field8,
            @NonNull SerializableField<P, A9> field9,
            @NonNull SerializableField<P, A10> field10
    ) {
        public <A> @NonNull B11<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A> add(@NonNull SerializableField<P, A> field) {
            return new B11<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, field);
        }

        public <A> @NonNull B11<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B11<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B11<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember(),
                            this.field7.getSerializedMember(),
                            this.field8.getSerializedMember(),
                            this.field9.getSerializedMember(),
                            this.field10.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B11<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6,
            @NonNull SerializableField<P, A7> field7,
            @NonNull SerializableField<P, A8> field8,
            @NonNull SerializableField<P, A9> field9,
            @NonNull SerializableField<P, A10> field10,
            @NonNull SerializableField<P, A11> field11
    ) {
        public <A> @NonNull B12<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A> add(@NonNull SerializableField<P, A> field) {
            return new B12<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, field);
        }

        public <A> @NonNull B12<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A> required(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter) {
            return this.add(new SimpleSerializableField<>(name, codec, getter));
        }

        public <A> @NonNull B12<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull Supplier<A> fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, fallback));
        }

        public <A> @NonNull B12<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A> optional(@NonNull String name, @NonNull Codec<A> codec, @NonNull Function<P, A> getter, @NonNull A fallback) {
            return this.add(new OptionalSerializableField<>(name, codec, getter, () -> fallback));
        }

        public @NonNull MapCodec<P> build(@NonNull Function11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember(),
                            this.field7.getSerializedMember(),
                            this.field8.getSerializedMember(),
                            this.field9.getSerializedMember(),
                            this.field10.getSerializedMember(),
                            this.field11.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }

    public record B12<P, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12>(
            @NonNull SerializableField<P, A1> field1,
            @NonNull SerializableField<P, A2> field2,
            @NonNull SerializableField<P, A3> field3,
            @NonNull SerializableField<P, A4> field4,
            @NonNull SerializableField<P, A5> field5,
            @NonNull SerializableField<P, A6> field6,
            @NonNull SerializableField<P, A7> field7,
            @NonNull SerializableField<P, A8> field8,
            @NonNull SerializableField<P, A9> field9,
            @NonNull SerializableField<P, A10> field10,
            @NonNull SerializableField<P, A11> field11,
            @NonNull SerializableField<P, A12> field12
    ) {
        public @NonNull MapCodec<P> build(@NonNull Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, P> constructor) {
            return RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            this.field1.getSerializedMember(),
                            this.field2.getSerializedMember(),
                            this.field3.getSerializedMember(),
                            this.field4.getSerializedMember(),
                            this.field5.getSerializedMember(),
                            this.field6.getSerializedMember(),
                            this.field7.getSerializedMember(),
                            this.field8.getSerializedMember(),
                            this.field9.getSerializedMember(),
                            this.field10.getSerializedMember(),
                            this.field11.getSerializedMember(),
                            this.field12.getSerializedMember()
                    )
                    .apply(instance, constructor)
            );
        }
    }
}
