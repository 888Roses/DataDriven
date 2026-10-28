package dev.rosenoire.data_driven.client.content.particular._modules.renderer;

import dev.rosenoire.data_driven.client.content.particular._modules.rendererv2.BillboardMode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

@Environment(EnvType.CLIENT)
public class ParticularRenderState {
    public Vec3 pos;
    public Identifier texture;
    public boolean isTranslucent;
    public BillboardMode billboardMode;
    public Quaternionf billboardQuaternion;
}
