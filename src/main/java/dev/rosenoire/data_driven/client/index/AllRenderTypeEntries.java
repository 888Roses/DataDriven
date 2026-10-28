package dev.rosenoire.data_driven.client.index;

import dev.rosenoire.data_driven.core.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface AllRenderTypeEntries {
    static void initialize() {
        RenderTypeRegistry.registerVanilla("entity_translucent", RenderTypes::entityTranslucent);
        RenderTypeRegistry.registerVanilla("entity_translucent_emissive", RenderTypes::entityTranslucentEmissive);
        RenderTypeRegistry.registerVanilla("entity_solid", RenderTypes::entitySolid);
        RenderTypeRegistry.registerVanilla("entity_cutout", RenderTypes::entityCutout);
        RenderTypeRegistry.registerVanilla("entity_cutout_cull", RenderTypes::entityCutoutCull);
        RenderTypeRegistry.registerVanilla("entity_cutout_z_offset", RenderTypes::entityCutoutZOffset);
        RenderTypeRegistry.registerVanilla("breeze_eyes", RenderTypes::breezeEyes);
        RenderTypeRegistry.registerVanilla("item_cutout", RenderTypes::itemCutout);
        RenderTypeRegistry.registerVanilla("item_translucent", RenderTypes::itemTranslucent);
    }
}
