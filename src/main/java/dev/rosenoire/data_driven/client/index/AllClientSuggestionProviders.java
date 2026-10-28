package dev.rosenoire.data_driven.client.index;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.rosenoire.data_driven.core.particular.storage.ParticularConfigRegistry;
import dev.rosenoire.data_driven.shared.DataDriven;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.synchronization.SuggestionProviders;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface AllClientSuggestionProviders {
    SuggestionProvider<SharedSuggestionProvider> PARTICULAR_CONFIGS = SuggestionProviders.register(
            DataDriven.id("particular_configs"),
            (_, builder) -> SharedSuggestionProvider.suggestResource(
                    ParticularConfigRegistry.CONFIG.keySet(),
                    builder
            )
    );

    static void initialize() {
    }
}
