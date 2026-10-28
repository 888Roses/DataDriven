package dev.rosenoire.data_driven.core.particular.level;

import dev.rosenoire.data_driven.core.particular.bridge.ParticularModuleConfigFactory;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiStatus.Internal
@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface ParticularGlobalStorage {
    Map<ClientLevel, ParticularLevel> LEVELS = new HashMap<>();
    Map<Class<? extends ParticularModuleConfig>, ParticularModuleConfigFactory<?>> FACTORIES = new HashMap<>();

    static void onLevelChanged(@NonNull Minecraft client,
                               @NonNull ClientLevel level) {
        LEVELS.putIfAbsent(level, new ParticularLevel(level));
    }

    static void onClientTick(@NonNull Minecraft client) {
        if (client.isPaused()) {
            return;
        }

        for (var entry : LEVELS.entrySet()) {
            var level = entry.getKey();
            var particularLevel = entry.getValue();

            particularLevel.tick(level == client.level);
        }
    }

    static void onRender(@NonNull LevelRenderContext context) {
        for (var entry : LEVELS.entrySet()) {
            var particularLevel = entry.getValue();
            particularLevel.submitRender(context);
        }
    }

    static void onExtract(@NonNull LevelExtractionContext context) {
        for (var entry : LEVELS.entrySet()) {
            var particularLevel = entry.getValue();
            particularLevel.extractRenderState(context);
        }
    }
}
