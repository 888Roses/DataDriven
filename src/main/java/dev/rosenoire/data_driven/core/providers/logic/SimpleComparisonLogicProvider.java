package dev.rosenoire.data_driven.core.providers.logic;

import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public abstract class SimpleComparisonLogicProvider implements LogicProvider {
    private final @NonNull NumericalProvider a;
    private final @NonNull NumericalProvider b;

    public SimpleComparisonLogicProvider(@NonNull NumericalProvider a, @NonNull NumericalProvider b) {
        this.a = a;
        this.b = b;
    }

    protected abstract boolean compare(int comparisonOutput);

    @Override
    public @NonNull Boolean compute(@NonNull ProviderContext context) {
        return this.compare(a.compute(context).compareTo(b.compute(context)));
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

        var other = (SimpleComparisonLogicProvider) obj;
        return Objects.equals(this.a, other.a) && Objects.equals(this.b, other.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "SimpleComparisonLogicProvider[a=%s, b=%s]".formatted(a, b);
    }
}