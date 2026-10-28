package dev.rosenoire.data_driven.core.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public interface FacingCameraMode {
    FacingCameraMode NONE = (_, _, _) -> {};
    FacingCameraMode HORIZONTAL = (rotation, camera, _) -> rotation.set(0, camera.rotation().y, 0, camera.rotation().w);
    FacingCameraMode VERTICAL = (rotation, camera, _) -> rotation.set(camera.rotation().x, 0, 0, camera.rotation().w);
    FacingCameraMode BOTH = (rotation, camera, _) -> rotation.set(camera.rotation());

    void setRotation(@NonNull Quaternionf rotation, @NonNull Camera camera, float partial);
}