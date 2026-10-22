package dev.rosenoire.data_driven.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class DataDrivenClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    }
}
