package dev.rosenoire.data_driven.client.content.particular._modules.rendererv2;

import com.mojang.serialization.MapCodec;
import dev.rosenoire.data_driven.client.index.AllParticularModuleTypes_V1;
import dev.rosenoire.data_driven.core.codecs.MapCodecBuilder;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfigType;
import dev.rosenoire.data_driven.core.providers.advanced.TransformationProvider;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record RendererModuleConfig2
        (@NonNull Identifier texture,
         @NonNull Identifier renderType,
         @NonNull BillboardMode billboard,
         @NonNull List<TransformationProvider> transformations)
        implements ParticularModuleConfig {

    public static final @NonNull MapCodec<RendererModuleConfig2> CODEC = MapCodecBuilder
            .<RendererModuleConfig2>builder()
            .required("texture", Identifier.CODEC, RendererModuleConfig2::texture)
            .optional("render_type", Identifier.CODEC, RendererModuleConfig2::renderType, Identifier.withDefaultNamespace("entity_translucent"))
            .optional("billboard", BillboardMode.CODEC, RendererModuleConfig2::billboard, BillboardMode.NONE)
            .optional("transformations", TransformationProvider.CODEC.codec().listOf(), RendererModuleConfig2::transformations, List.of())
            .build(RendererModuleConfig2::new);

    @Override
    public @NonNull ParticularModuleConfigType<?> getType() {
        return AllParticularModuleTypes_V1.RENDERER2;
    }
}
