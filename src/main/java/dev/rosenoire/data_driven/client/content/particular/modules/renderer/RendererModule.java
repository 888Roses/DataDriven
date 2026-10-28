package dev.rosenoire.data_driven.client.content.particular.modules.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.RendererModuleRenderState;
import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import dev.rosenoire.data_driven.core.rendering.RenderTypeRegistry;
import dev.rosenoire.data_driven.core.rendering.TransformationHelper;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.LightCoordsUtil;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

public class RendererModule extends ParticularModule<RendererModuleConfig> {
    protected dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.RendererModuleRenderState renderState;

    public RendererModule(@NonNull Particular particular, @NonNull RendererModuleConfig config) {
        super(particular, config);
    }

    @Override
    public void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel) {
        // Gizmos.point(this.particular.pos, -1, 10);
    }

    @Override
    public void extractRenderState(@NonNull LevelExtractionContext context) {
        super.extractRenderState(context);

        var tracker = context.deltaTracker();
        var partialTicks = tracker.getGameTimeDeltaPartialTick(false);
        var cameraPos = context.camera().position();

        this.renderState = new RendererModuleRenderState();

        var renderType = RenderTypeRegistry
                .renderType(this.config.renderType())
                .orElse(RenderTypes::entityTranslucent);
        var texture = this.config.texture()
                .withPath("textures/particular/%s.png"::formatted);
        this.renderState.renderType = renderType.apply(texture);

        this.renderState.position = this.particular.oldPos.lerp(this.particular.pos, partialTicks).subtract(cameraPos);
        this.renderState.transformation = TransformationHelper.combine(this.config
                .transformations()
                .stream()
                .map(transformation -> transformation.compute(new ProviderContext(
                        context.level().getRandom(),
                        this.particular.variables
                )))
                .toList()
        );

        var billboardRotation = new Quaternionf();
        this.config.billboard().setRotation(billboardRotation, context.camera(), partialTicks);
        this.renderState.billboardRotation = billboardRotation;
    }

    @Override
    public void submitRender(@NonNull PoseStack stack, @NonNull SubmitNodeCollector collector, @NonNull LevelRenderContext context) {
        super.submitRender(stack, collector, context);
        if (this.renderState == null) return;

        stack.pushPose();
        stack.translate(this.renderState.position);
        stack.rotate(this.renderState.billboardRotation);
        stack.mulPose(this.renderState.transformation);
        stack.translate(-0.5, -0.5, 0);

        collector.submitCustomGeometry(stack, this.renderState.renderType, (pose, buffer) -> {
            buffer.addVertex(pose, 0, 0, 0)
                    .setNormal(pose, 0, 1, 0)
                    .setLight(LightCoordsUtil.FULL_BRIGHT)
                    .setColor(-1)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setUv(0, 0);

            buffer.addVertex(pose, 1, 0, 0)
                    .setNormal(pose, 0, 1, 0)
                    .setLight(LightCoordsUtil.FULL_BRIGHT)
                    .setColor(-1)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setUv(1, 0);

            buffer.addVertex(pose, 1, 1, 0)
                    .setNormal(pose, 0, 1, 0)
                    .setLight(LightCoordsUtil.FULL_BRIGHT)
                    .setColor(-1)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setUv(1, 1);

            buffer.addVertex(pose, 0, 1, 0)
                    .setNormal(pose, 0, 1, 0)
                    .setLight(LightCoordsUtil.FULL_BRIGHT)
                    .setColor(-1)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setUv(0, 1);
        });

        stack.popPose();
    }
}
