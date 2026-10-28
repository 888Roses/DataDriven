package dev.rosenoire.data_driven.core.codecs.fields;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public record SimpleSerializableField<P, V>
        (@NonNull String name, @NonNull Codec<V> codec, @NonNull Function<P, V> getter)
        implements SerializableField<P, V> {

    @Override
    public @NonNull RecordCodecBuilder<P, V> getSerializedMember() {
        return this.codec().fieldOf(this.name()).forGetter(this.getter());
    }
}
