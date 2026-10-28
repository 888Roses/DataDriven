package dev.rosenoire.data_driven.core.codecs.fields;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

/// @param <P> Represents the type of the parent type of this serialisable field. In other words, this is the type hosting
/// this field, from which the [#getter()] retrieves it.
/// @param <V> Represents the type of the value represented by this field.
public interface SerializableField<P, V> {
    @NonNull String name();

    @NonNull Codec<V> codec();

    @NonNull Function<P, V> getter();

    @NonNull RecordCodecBuilder<P, V> getSerializedMember();
}
