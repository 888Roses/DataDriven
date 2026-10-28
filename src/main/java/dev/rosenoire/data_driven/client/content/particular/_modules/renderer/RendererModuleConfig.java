package dev.rosenoire.data_driven.client.content.particular._modules.renderer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.BillboardMode;
import dev.rosenoire.data_driven.client.index.AllParticularModuleTypes_V1;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public record RendererModuleConfig
        (@NonNull Identifier textureLocation, boolean isTranslucent, @NonNull BillboardMode billboardMode)
        implements ParticularModuleConfig {

    public static final @NonNull MapCodec<RendererModuleConfig> CODEC = MapCodecBuilder
            .<RendererModuleConfig>builder()
            .required("texture", Identifier.CODEC, RendererModuleConfig::textureLocation)
            .optional("translucent", Codec.BOOL, RendererModuleConfig::isTranslucent, false)
            .optional("billboard", BillboardMode.CODEC, RendererModuleConfig::billboardMode, BillboardMode.NONE)
            .build(RendererModuleConfig::new);

    @Override
    public @NonNull ParticularModuleConfigType<?> getType() {
        return AllParticularModuleTypes_V1.RENDERER;
    }
}
