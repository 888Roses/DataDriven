package dev.rosenoire.data_driven.core.providers.logic.impl;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.shared.index.AllLogicProviders;
import org.jspecify.annotations.NonNull;

public record ConstantLogicProvider(boolean value) implements LogicProvider {
    public static final @NonNull MapCodec<ConstantLogicProvider> CODEC = MapCodecBuilder
            .<ConstantLogicProvider>builder()
            .required("value", Codec.BOOL, ConstantLogicProvider::value)
            .build(ConstantLogicProvider::new);

    @Override
    public @NonNull LogicProviderType<?> getType() {
        return AllLogicProviders.CONSTANT;
    }

    @Override
    public @NonNull Boolean compute(@NonNull ProviderContext context) {
        return this.value();
    }
}
