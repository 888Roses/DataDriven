package dev.rosenoire.data_driven.client.content.particular._modules.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.LightCoordsUtil;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class RendererModule extends ParticularModule<RendererModuleConfig> {
    protected @NonNull ParticularRenderState renderState = new ParticularRenderState();

    public RendererModule(@NonNull Particular particular, @NonNull RendererModuleConfig config) {
        super(particular, config);
    }

    @Override
    public void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel) {
        //Gizmos.point(this.particular.pos, -1, 10);
    }

    @Override
    public void extractRenderState(@NonNull LevelExtractionContext context) {
        var partial = context.deltaTracker().getGameTimeDeltaPartialTick(false);
        var cameraPos = context.camera().position();

        this.renderState = new ParticularRenderState();
        renderState.texture = this.config.textureLocation()
                .withPrefix("textures/particular/")
                .withSuffix(".png");

        renderState.isTranslucent = this.config.isTranslucent();
        renderState.pos = this.particular.oldPos.lerp(this.particular.pos, partial).subtract(cameraPos);

        renderState.billboardMode = this.config.billboardMode();
        renderState.billboardQuaternion = new Quaternionf();
        renderState.billboardMode.setRotation(renderState.billboardQuaternion, context.camera(), partial);
    }

    @Override
    public void submitRender(@NonNull PoseStack stack,
                             @NonNull SubmitNodeCollector collector,
                             @NonNull LevelRenderContext context) {
        stack.pushPose();

        stack.translate(renderState.pos);
        stack.rotate(this.renderState.billboardQuaternion);
        stack.translate(-0.5, -0.5, 0);

        var renderType = renderState.isTranslucent
                ? RenderTypes.entityTranslucent(renderState.texture)
                : RenderTypes.entitySolid(renderState.texture);
        collector.submitCustomGeometry(stack, renderType, (pose, buffer) -> {
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
