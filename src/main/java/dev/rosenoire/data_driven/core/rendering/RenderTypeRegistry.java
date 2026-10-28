package dev.rosenoire.data_driven.core.rendering;

import dev.rosenoire.data_driven.shared.DataDriven;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class RenderTypeRegistry {
    private static final @NonNull Map<Identifier, Function<Identifier, RenderType>> REGISTRY = new HashMap<>();

    private RenderTypeRegistry() {
    }

    public static void register(@NonNull Identifier identifier,
                                @NonNull Function<Identifier, RenderType> renderType) {
        REGISTRY.put(identifier, renderType);
    }

    public static void registerVanilla(@NonNull String name,
                                       @NonNull Function<Identifier, RenderType> renderType) {
        register(Identifier.withDefaultNamespace(name), renderType);
    }

    public static void registerDataDriven(@NonNull String name,
                                          @NonNull Function<Identifier, RenderType> renderType) {
        register(DataDriven.id(name), renderType);
    }

    public static @NonNull Optional<Function<Identifier, RenderType>> renderType(
            @NonNull Identifier identifier
    ) {
        return Optional.ofNullable(REGISTRY.get(identifier));
    }
}
