package dev.rosenoire.data_driven.core.providers.logic;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class MultiInputLogicProvider implements LogicProvider {
    public static <T extends MultiInputLogicProvider> @NonNull MapCodec<T> createMultiInputCodec(
            @NonNull Function<T, List<LogicProvider>> valuesGetter,
            @NonNull Function<List<LogicProvider>, T> constructor
    ) {
        return RecordCodecBuilder
                .mapCodec(instance -> instance
                        .group(
                                LogicProvider
                                        .CODEC
                                        .listOf()
                                        .fieldOf("values")
                                        .forGetter(valuesGetter)
                        )
                        .apply(instance, constructor)
                );
    }

    private final @NonNull List<LogicProvider> values;

    public MultiInputLogicProvider(@NonNull List<LogicProvider> values) {
        this.values = values;
    }

    public @NonNull List<LogicProvider> values() {
        return this.values;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        var other = (MultiInputLogicProvider) obj;
        return Objects.equals(this.values, other.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "MultiInputLogicValueProvider[values=%s]".formatted(values);
    }

}