package dev.rosenoire.data_driven.client.content.particular.modules.renderer;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.BillboardMode;
import dev.rosenoire.data_driven.client.index.AllParticularModuleTypes;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.core.providers.advanced.TransformationProvider;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record RendererModuleConfig
        (@NonNull Identifier texture,
         @NonNull Identifier renderType,
         @NonNull BillboardMode billboard,
         @NonNull List<TransformationProvider> transformations)
        implements ParticularModuleConfig {

    public static final @NonNull MapCodec<RendererModuleConfig> MAP_CODEC = MapCodecBuilder
            .<RendererModuleConfig>builder()
            .required("texture", Identifier.CODEC, RendererModuleConfig::texture)
            .optional("render_type", Identifier.CODEC, RendererModuleConfig::renderType, Identifier.withDefaultNamespace("entity_translucent"))
            .optional("billboard", BillboardMode.CODEC, RendererModuleConfig::billboard, BillboardMode.NONE)
            .optional("transformations", TransformationProvider.CODEC.codec().listOf(), RendererModuleConfig::transformations, List.of())
            .build(RendererModuleConfig::new);

    @Override
    public @NonNull ParticularModuleConfigType<?> getType() {
        return AllParticularModuleTypes.RENDERER;
    }
}
