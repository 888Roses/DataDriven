package dev.rosenoire.data_driven.shared.index;

import dev.rosenoire.data_driven.shared.DataDriven;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v8.component.CardinalComponent;

@ApiStatus.NonExtendable
public interface AllComponentKeys {
    private static <C extends CardinalComponent> @NonNull ComponentKey<C> key(
            @NonNull String name,
            @NonNull Class<C> clazz
    ) {
        return ComponentRegistry.getOrCreate(DataDriven.id(name), clazz);
    }
}
