package dev.rosenoire.data_driven.core.cca;

import dev.rosenoire.data_driven.shared.DataDriven;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SubComponentStorage {
    public final @NonNull SmartComponent<?> parent;
    public final @NonNull Map<Identifier, SubCardinalComponent> storage = this.createStorageMap();

    public SubComponentStorage(@NonNull SmartComponent<?> parent) {
        this.parent = parent;
    }

    protected @NonNull Map<Identifier, SubCardinalComponent> createStorageMap() {
        return new HashMap<>();
    }

    public <S extends SubCardinalComponent> @NonNull S add(@NonNull Identifier identifier,
                                                           @NonNull S subComponent) {
        this.storage.put(identifier, subComponent);
        return subComponent;
    }

    public <S extends SubCardinalComponent> @NonNull S add(@NonNull String modPath,
                                                           @NonNull S subComponent) {
        return this.add(DataDriven.id(modPath), subComponent);
    }

    public @Nullable SubCardinalComponent remove(@NonNull Identifier identifier) {
        return this.storage.remove(identifier);
    }

    public @NonNull Optional<SubCardinalComponent> optional(@NonNull Identifier identifier) {
        return Optional.ofNullable(this.storage.get(identifier));
    }

    public @Nullable SubCardinalComponent get(@NonNull Identifier identifier) {
        return this.optional(identifier).orElse(null);
    }
}
