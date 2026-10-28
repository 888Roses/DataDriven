package dev.rosenoire.data_driven.core.providers.logic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.rosenoire.data_driven.core.AllRegistries;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.providers.ValueProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import org.jspecify.annotations.NonNull;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface LogicProvider extends ValueProvider<Boolean> {
    Codec<LogicProvider> CODEC = AllRegistries.LOGIC_PROVIDERS
            .byNameCodec()
            .dispatch("type", LogicProvider::getType, LogicProviderType::mapCodec);

    static <T extends LogicProvider> @NonNull MapCodec<T> createABCodec(
            @NonNull Function<T, NumericalProvider> aGetter,
            @NonNull Function<T, NumericalProvider> bGetter,
            @NonNull BiFunction<NumericalProvider, NumericalProvider, T> constructor
    ) {
        return RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        NumericalProvider.CODEC
                                .fieldOf("a")
                                .forGetter(aGetter),
                        NumericalProvider.CODEC
                                .fieldOf("b")
                                .forGetter(bGetter)
                )
                .apply(instance, constructor)
        );
    }

    @Override
    @NonNull Boolean compute(@NonNull ProviderContext context);

    @NonNull LogicProviderType<?> getType();
}
