package dev.rosenoire.data_driven.core.codecs.fields;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public record OptionalSerializableField<P, V>
        (@NonNull String name, @NonNull Codec<V> codec, @NonNull Function<P, V> getter, @NonNull Supplier<V> fallback)
        implements SerializableField<P, V> {

    @Override
    public @NonNull RecordCodecBuilder<P, V> getSerializedMember() {
        return this.codec()
                .optionalFieldOf(this.name())
                .xmap(x -> x.orElse(this.fallback.get()), Optional::of)
                .forGetter(this.getter());
    }
}
