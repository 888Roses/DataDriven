package dev.rosenoire.data_driven.shared;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;
import org.ladysnake.cca.api.v8.level.LevelComponentFactoryRegistry;
import org.ladysnake.cca.api.v8.level.LevelComponentInitializer;

@ApiStatus.NonExtendable
public class DataDrivenLevelComponentInitializer implements LevelComponentInitializer {
    @Override
    public void registerLevelComponentFactories(@NonNull LevelComponentFactoryRegistry registry) {
    }
}
