package dev.rosenoire.data_driven.core.rendering;

import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.util.StringRepresentable;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public enum BillboardMode implements FacingCameraMode, StringRepresentable {
    NONE("none", FacingCameraMode.NONE),
    HORIZONTAL("horizontal", FacingCameraMode.HORIZONTAL),
    VERTICAL("vertical", FacingCameraMode.VERTICAL),
    BOTH("both", FacingCameraMode.BOTH),

    ;

    public static final @NonNull Codec<BillboardMode> CODEC = StringRepresentable
            .fromEnum(BillboardMode::values);

    private final @NonNull String serializedName;
    private final @NonNull FacingCameraMode facingCameraMode;

    BillboardMode(@NonNull String serializedName, @NonNull FacingCameraMode facingCameraMode) {
        this.serializedName = serializedName;
        this.facingCameraMode = facingCameraMode;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.serializedName;
    }

    @Override
    public void setRotation(@NonNull Quaternionf rotation, @NonNull Camera camera, float partial) {
        this.facingCameraMode.setRotation(rotation, camera, partial);
    }
}
