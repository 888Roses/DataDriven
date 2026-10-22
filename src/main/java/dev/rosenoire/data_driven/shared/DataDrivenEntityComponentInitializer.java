package dev.rosenoire.data_driven.shared;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

import static dev.rosenoire.data_driven.shared.index.AllComponentKeys.*;

@ApiStatus.NonExtendable
public class DataDrivenEntityComponentInitializer implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(@NonNull EntityComponentFactoryRegistry registry) {
    }
}
