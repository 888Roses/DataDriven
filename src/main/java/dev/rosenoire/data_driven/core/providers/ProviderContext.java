package dev.rosenoire.data_driven.core.providers;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

public record ProviderContext(@NonNull RandomSource random, @NonNull Map<String, Object> variables) {
    public double uniform(double min, double max) {
        return Mth.nextDouble(random, min, max);
    }

    public <T> @NonNull Optional<T> get(@NonNull Class<T> clazz, @NonNull String name) {
        //noinspection unchecked
        return Optional.ofNullable(this.variables.get(name))
                .map(x -> clazz.isInstance(x) ? (T) x : null);
    }

    public @NonNull Optional<Double> getDouble(@NonNull String name) {
        return this.get(Double.class, name);
    }

    public double getDoubleOr(@NonNull String name, double fallback) {
        return this.getDouble(name).orElse(fallback);
    }

    public @NonNull Optional<Float> getFloat(@NonNull String name) {
        return this.get(Float.class, name);
    }

    public float getFloatOr(@NonNull String name, float fallback) {
        return this.getFloat(name).orElse(fallback);
    }

    public @NonNull Optional<Long> getLong(@NonNull String name) {
        return this.get(Long.class, name);
    }

    public long getLongOr(@NonNull String name, long fallback) {
        return this.getLong(name).orElse(fallback);
    }

    public @NonNull Optional<Integer> getInt(@NonNull String name) {
        return this.get(Integer.class, name);
    }

    public int getIntOr(@NonNull String name, int fallback) {
        return this.getInt(name).orElse(fallback);
    }

    public @NonNull Optional<String> getString(@NonNull String name) {
        return this.get(String.class, name);
    }

    public @NonNull String getStringOr(@NonNull String name, @NonNull String fallback) {
        return this.getString(name).orElse(fallback);
    }

    public @NonNull Optional<Boolean> getBool(@NonNull String name) {
        return this.get(Boolean.class, name);
    }

    public boolean getBoolOr(@NonNull String name, boolean fallback) {
        return this.getBool(name).orElse(fallback);
    }
}
