package dev.rosenoire.data_driven.core.providers.numerical;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.Function;

public abstract class DynamicInputNumericalProvider implements NumericalProvider {
    public static <T extends DynamicInputNumericalProvider> @NonNull MapCodec<T> simpleCodec(
            @NonNull Function<NumericalProvider, T> constructor
    ) {
        return SimpleCodecs.mapCodec(
                "value", CODEC, DynamicInputNumericalProvider::value,
                constructor
        );
    }

    private final @NonNull NumericalProvider value;

    public DynamicInputNumericalProvider(@NonNull NumericalProvider value) {
        this.value = value;
    }

    public @NonNull NumericalProvider value() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        var other = (DynamicInputNumericalProvider) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return "InputNumericalProvider[value=%s]".formatted(this.value);
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        return this.compute(context, this.value().compute(context));
    }

    protected abstract double compute(@NonNull ProviderContext context, double value);
}