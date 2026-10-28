package dev.rosenoire.data_driven.core.providers.numerical;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.SimpleCodecs;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.BiFunction;

public abstract class BiDynamicInputNumericalProvider implements NumericalProvider {
    public static <T extends BiDynamicInputNumericalProvider> @NonNull MapCodec<T> simpleCodec(
            @NonNull BiFunction<NumericalProvider, NumericalProvider, T> constructor
    ) {
        return SimpleCodecs.mapCodec(
                "a", CODEC, BiDynamicInputNumericalProvider::a,
                "b", CODEC, BiDynamicInputNumericalProvider::b,
                constructor
        );
    }

    private final @NonNull NumericalProvider a;
    private final @NonNull NumericalProvider b;

    public BiDynamicInputNumericalProvider(@NonNull NumericalProvider a, @NonNull NumericalProvider b) {
        this.a = a;
        this.b = b;
    }

    public @NonNull NumericalProvider a() {
        return this.a;
    }

    public @NonNull NumericalProvider b() {
        return this.b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        var other = (BiDynamicInputNumericalProvider) obj;
        return Objects.equals(this.a, other.a) && Objects.equals(this.b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    @Override
    public String toString() {
        return "BiInputNumericalProvider[a=%s, b=%s]".formatted(this.a, this.b);
    }

    @Override
    public @NonNull Double compute(@NonNull ProviderContext context) {
        return this.compute(context, this.a().compute(context), this.b().compute(context));
    }

    protected abstract double compute(@NonNull ProviderContext context, double a, double b);
}
