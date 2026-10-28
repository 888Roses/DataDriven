package dev.rosenoire.data_driven.client;

import dev.rosenoire.data_driven.client.index.*;
import dev.rosenoire.data_driven.core.particular.level.ParticularGlobalStorage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class DataDrivenClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AllParticularModuleTypes.initialize();
        AllParticularModuleConfigFactories.initialize();

        AllClientCommands.initialize();
        AllClientSuggestionProviders.initialize();
        AllRenderTypeEntries.initialize();

        initializeHooks();
    }

    private static void initializeHooks() {
        ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register(ParticularGlobalStorage::onLevelChanged);
        ClientTickEvents.START_CLIENT_TICK.register(ParticularGlobalStorage::onClientTick);
        LevelExtractionEvents.END_EXTRACTION.register(ParticularGlobalStorage::onExtract);
        LevelRenderEvents.AFTER_TRANSLUCENT_TERRAIN.register(ParticularGlobalStorage::onRender);
    }
}
