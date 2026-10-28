package dev.rosenoire.data_driven.core.providers;

import org.jspecify.annotations.NonNull;

public interface ValueProvider<T> {
    @NonNull T compute(@NonNull ProviderContext context);
}
