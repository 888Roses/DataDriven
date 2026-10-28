package dev.rosenoire.data_driven.core.particular.storage;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

import java.util.Map;

@Environment(EnvType.CLIENT)
public final class ParticularConfigRegistryReloadListener extends SimpleJsonResourceReloadListener<ParticularConfig> {
    public static final @NonNull Logger LOGGER = LogUtils.getLogger();
    public static final @NonNull FileToIdConverter LISTER = FileToIdConverter.json("particular");

    public ParticularConfigRegistryReloadListener() {
        super(ParticularConfig.CODEC, LISTER);
    }

    @Override
    protected void apply(@NonNull Map<Identifier, ParticularConfig> preparations,
                         @NonNull ResourceManager manager,
                         @NonNull ProfilerFiller profiler) {
        ParticularConfigRegistry.CONFIG.clear();
        ParticularConfigRegistry.CONFIG.putAll(preparations);

        for (var preparationEntry : preparations.entrySet()) {
            LOGGER.info("Registered particular config: \"{}\"", preparationEntry.getKey());
        }
    }
}
