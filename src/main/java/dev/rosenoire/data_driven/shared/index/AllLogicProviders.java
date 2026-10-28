package dev.rosenoire.data_driven.shared.index;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.AllRegistries;
import dev.rosenoire.data_driven.core.providers.logic.LogicProvider;
import dev.rosenoire.data_driven.core.providers.logic.LogicProviderType;
import dev.rosenoire.data_driven.core.providers.logic.impl.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

@ApiStatus.NonExtendable
public interface AllLogicProviders {
    LogicProviderType<ConstantLogicProvider> CONSTANT = provider("constant", ConstantLogicProvider.CODEC);
    LogicProviderType<AllLogicProvider> ALL = provider("all", AllLogicProvider.CODEC);
    LogicProviderType<AnyLogicProvider> ANY = provider("any", AnyLogicProvider.CODEC);
    LogicProviderType<NoneLogicProvider> NONE = provider("none", NoneLogicProvider.CODEC);
    LogicProviderType<EqualLogicProvider> EQUAL = provider("equal", EqualLogicProvider.CODEC);
    LogicProviderType<GreaterLogicProvider> GREATER = provider("greater", GreaterLogicProvider.CODEC);
    LogicProviderType<GreaterEqualLogicProvider> GREATER_EQUAL = provider("greater_equal", GreaterEqualLogicProvider.CODEC);
    LogicProviderType<LesserLogicProvider> LESSER = provider("lesser", LesserLogicProvider.CODEC);
    LogicProviderType<LesserEqualLogicProvider> LESSER_EQUAL = provider("lesser_equal", LesserEqualLogicProvider.CODEC);

    static void initialize() {
    }

    private static <T extends LogicProvider> @NonNull LogicProviderType<T> provider(
            @NonNull String name,
            @NonNull MapCodec<T> mapCodec
    ) {
        return Registry.register(
                AllRegistries.LOGIC_PROVIDERS,
                Identifier.withDefaultNamespace(name),
                new LogicProviderType<>(mapCodec)
        );
    }
}
