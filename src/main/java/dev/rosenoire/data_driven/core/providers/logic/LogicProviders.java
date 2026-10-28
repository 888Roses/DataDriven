package dev.rosenoire.data_driven.core.providers.logic;

import dev.rosenoire.data_driven.core.providers.logic.impl.ConstantLogicProvider;
import org.jspecify.annotations.NonNull;

public final class LogicProviders {
    private LogicProviders() {
    }

    public static @NonNull LogicProvider constant(boolean value) {
        return new ConstantLogicProvider(value);
    }
}
