package dev.rosenoire.data_driven.core.particular.level;

import dev.rosenoire.data_driven.core.particular.bridge.ParticularModuleConfigFactory;
import dev.rosenoire.data_driven.core.particular.storage.ParticularConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public final class ParticularManager {
    private ParticularManager() {
    }

    public static <C extends ParticularModuleConfig> void registerModuleConfigFactory(
            @NonNull Class<C> clazz,
            @NonNull ParticularModuleConfigFactory<C> factory
    ) {
        ParticularGlobalStorage.FACTORIES.put(clazz, factory);
    }

    public static @NonNull ParticularLevel getLevel(@NonNull ClientLevel clientLevel) {
        return ParticularGlobalStorage.LEVELS.get(clientLevel);
    }

    public static @NonNull Particular add(@NonNull ClientLevel clientLevel, @NonNull ParticularConfig config) {
        var particularLevel = getLevel(clientLevel);
        var particular = new Particular(config, new ArrayList<>());
        particular.setLevel(particularLevel);

        for (var moduleConfig : config.modules) {
            for (var entry : ParticularGlobalStorage.FACTORIES.entrySet()) {
                if (entry.getKey().isInstance(moduleConfig)) {
                    particular.modules.add(entry.getValue().create(particular, moduleConfig));
                }
            }
        }

        getLevel(clientLevel).add(particular);
        return particular;
    }
}
