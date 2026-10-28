package dev.rosenoire.data_driven.client.content.particular._modules.rendererv2;

import com.mojang.math.Transformation;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class RendererModuleRenderState {
    public Vec3 position;
    public Transformation transformation;
    public Quaternionf billboardRotation;
    public RenderType renderType;
}
