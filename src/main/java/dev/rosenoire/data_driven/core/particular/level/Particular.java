package dev.rosenoire.data_driven.core.particular.level;

import dev.rosenoire.data_driven.core.particular.storage.ParticularConfig;
import dev.rosenoire.data_driven.core.providers.ProviderContext;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.List;

/// Represents a particular particle as can be seen in a `Level`.
@Environment(EnvType.CLIENT)
public class Particular {
    protected ParticularLevel level;
    public final @NonNull ParticularConfig config;
    public final @NonNull List<ParticularModule<?>> modules;
    public final @NonNull HashMap<String, Object> variables = new HashMap<>();

    protected boolean cachedDiscardedState;

    public @NonNull Vec3 pos = Vec3.ZERO;
    public @NonNull Vec3 oldPos = Vec3.ZERO;

    public Particular(@NonNull ParticularConfig config,
                      @NonNull List<ParticularModule<?>> modules) {
        this.config = config;
        this.modules = modules;
    }

    public ParticularLevel getLevel() {
        return level;
    }

    public void setLevel(@NonNull ParticularLevel level) {
        this.level = level;
    }

    public void setPos(@NonNull Vec3 pos) {
        this.pos = pos;
    }

    public void setPos(double x, double y, double z) {
        this.setPos(new Vec3(x, y, z));
    }

    public void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel) {
        this.oldPos = pos;

        for (var module : this.modules) {
            module.tick(clientLevel, isLocalLevel);
        }
    }

    public void submitRender(@NonNull LevelRenderContext context) {
        for (var module : this.modules) {
            module.submitRender(context.poseStack(), context.submitNodeCollector(), context);
        }
    }

    public void extractRenderState(@NonNull LevelExtractionContext context) {
        for (var module : this.modules) {
            module.extractRenderState(context);
        }
    }

    public boolean isDiscarded() {
        if (this.cachedDiscardedState) {
            return true;
        }

        for (var module : this.modules) {
            if (module.isDiscarded()) {
                this.cachedDiscardedState = true;
                return true;
            }
        }

        return false;
    }

    public @NonNull ProviderContext createProviderContext() {
        return new ProviderContext(
                this.level.getLevel().getRandom(),
                this.variables
        );
    }

    public <T> void setVariable(@NonNull String name, @NonNull T value) {
        this.variables.put(name, value);
    }
}
