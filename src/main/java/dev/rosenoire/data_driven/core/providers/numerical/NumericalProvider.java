package dev.rosenoire.data_driven.core.providers.numerical;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import dev.rosenoire.data_driven.core.AllRegistries;
import dev.rosenoire.data_driven.core.providers.ValueProvider;
import dev.rosenoire.data_driven.core.providers.numerical.impl.generators.ConstantNumericalProvider;
import org.jspecify.annotations.NonNull;

public interface NumericalProvider extends ValueProvider<Double> {
    Codec<Either<Double, NumericalProvider>> CONSTANT_OR_DISPATCH_CODEC = Codec.either(
            Codec.DOUBLE,
            AllRegistries.NUMERICAL_PROVIDERS
                    .byNameCodec()
                    .dispatch("type", NumericalProvider::getType, NumericalProviderType::mapCodec)
    );

    Codec<NumericalProvider> CODEC = CONSTANT_OR_DISPATCH_CODEC.xmap(
            either -> either.map(ConstantNumericalProvider::new, p -> p),
            p -> p instanceof ConstantNumericalProvider(double value) ? Either.left(value) : Either.right(p)
    );

    @NonNull NumericalProviderType<?> getType();
}
