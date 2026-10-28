package dev.rosenoire.data_driven.core.particular.storage;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public final class ParticularConfigRegistry {
    public static final @NonNull Map<Identifier, ParticularConfig> CONFIG = new HashMap<>();

    private ParticularConfigRegistry() {
    }

    public static boolean isRegistered(@NonNull Identifier identifier) {
        return CONFIG.containsKey(identifier);
    }

    public static @NonNull ParticularConfig get(@NonNull Identifier identifier) {
        return CONFIG.get(identifier);
    }
}
