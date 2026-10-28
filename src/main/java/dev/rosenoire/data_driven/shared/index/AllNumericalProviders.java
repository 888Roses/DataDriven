package dev.rosenoire.data_driven.shared.index;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.core.AllRegistries;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProviderType;
import dev.rosenoire.data_driven.core.providers.numerical.impl.conditional.IfNumericalProvider;
import dev.rosenoire.data_driven.core.providers.numerical.impl.generators.*;
import dev.rosenoire.data_driven.core.providers.numerical.impl.math.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

@ApiStatus.NonExtendable
public interface AllNumericalProviders {
    // Value Generators
    NumericalProviderType<ConstantNumericalProvider> CONSTANT = provider("constant", ConstantNumericalProvider.CODEC);
    NumericalProviderType<UniformNumericalProvider> UNIFORM = provider("uniform", UniformNumericalProvider.CODEC);
    NumericalProviderType<VarNumericalProvider> VAR = provider("var", VarNumericalProvider.CODEC);

    // Conditional
    NumericalProviderType<IfNumericalProvider> IF = provider("if", IfNumericalProvider.CODEC);

    // Math Modifiers
    NumericalProviderType<SumNumericalProvider> SUM = provider("sum", SumNumericalProvider.CODEC);
    NumericalProviderType<SubNumericalProvider> SUB = provider("sub", SubNumericalProvider.CODEC);
    NumericalProviderType<MulNumericalProvider> MUL = provider("mul", MulNumericalProvider.CODEC);
    NumericalProviderType<DivNumericalProvider> DIV = provider("div", DivNumericalProvider.CODEC);
    NumericalProviderType<ModNumericalProvider> MOD = provider("mod", ModNumericalProvider.CODEC);
    NumericalProviderType<AbsNumericalProvider> ABS = provider("abs", AbsNumericalProvider.CODEC);
    NumericalProviderType<SinNumericalProvider> SIN = provider("sin", SinNumericalProvider.CODEC);
    NumericalProviderType<CosNumericalProvider> COS = provider("cos", CosNumericalProvider.CODEC);
    NumericalProviderType<TanNumericalProvider> TAN = provider("tan", TanNumericalProvider.CODEC);
    NumericalProviderType<AsinNumericalProvider> ASIN = provider("asin", AsinNumericalProvider.CODEC);
    NumericalProviderType<AcosNumericalProvider> ACOS = provider("acos", AcosNumericalProvider.CODEC);
    NumericalProviderType<AtanNumericalProvider> ATAN = provider("atan", AtanNumericalProvider.CODEC);
    NumericalProviderType<RoundNumericalProvider> ROUND = provider("round", RoundNumericalProvider.CODEC);
    NumericalProviderType<FloorNumericalProvider> FLOOR = provider("floor", FloorNumericalProvider.CODEC);
    NumericalProviderType<CeilNumericalProvider> CEIL = provider("ceil", CeilNumericalProvider.CODEC);
    NumericalProviderType<LerpNumericalProvider> LERP = provider("lerp", LerpNumericalProvider.CODEC);

    static void initialize() {
    }

    private static <T extends NumericalProvider> @NonNull NumericalProviderType<T> provider(
            @NonNull String name,
            @NonNull MapCodec<T> mapCodec
    ) {
        return Registry.register(
                AllRegistries.NUMERICAL_PROVIDERS,
                Identifier.withDefaultNamespace(name),
                new NumericalProviderType<>(mapCodec)
        );
    }
}
