package dev.rosenoire.data_driven.core.providers.logic.impl;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.core.providers.logic.MultiInputLogicProvider;
import dev.rosenoire.data_driven.shared.index.AllLogicProviders;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class NoneLogicProvider extends MultiInputLogicProvider {
    public static final @NonNull MapCodec<NoneLogicProvider> CODEC =
            createMultiInputCodec(NoneLogicProvider::values, NoneLogicProvider::new);

    public NoneLogicProvider(@NonNull List<LogicProvider> values) {
        super(values);
    }

    @Override
    public @NonNull LogicProviderType<?> getType() {
        return AllLogicProviders.NONE;
    }

    @Override
    public @NonNull Boolean compute(@NonNull ProviderContext context) {
        for (var value : this.values()) {
            if (value.compute(context)) {
                return false;
            }
        }

        return true;
    }
}
