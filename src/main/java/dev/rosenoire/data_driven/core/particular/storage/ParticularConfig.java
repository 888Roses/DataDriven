package dev.rosenoire.data_driven.core.particular.storage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

/// Represents the information of a `Particular`, stored on the client side and passed to the `ParticularManager` to create
/// a new `Particular` instance to be rendered in a `Level`.
///
/// It contains a collection of modules (see [ParticularModuleConfig]), dictating how this particle should behave and look.
@Environment(EnvType.CLIENT)
public class ParticularConfig {
    public static final @NonNull Codec<ParticularConfig> CODEC = RecordCodecBuilder.create(
            i -> i.group(
                            ParticularModuleConfig.MODULE_CODEC
                                    .listOf()
                                    .fieldOf("modules")
                                    .forGetter(config -> config.modules)
                    )
                    .apply(i, modules -> {
                        var config = new ParticularConfig();
                        config.modules.addAll(modules);
                        return config;
                    })
    );

    public final @NonNull List<ParticularModuleConfig> modules = new ArrayList<>();
}
