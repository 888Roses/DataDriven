package dev.rosenoire.data_driven.core.particular.level;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ParticularLevel {
    protected final @NonNull List<Particular> particulars = new ArrayList<>(1000);
    protected final @NonNull ClientLevel level;

    public ParticularLevel(@NonNull ClientLevel level) {
        this.level = level;
    }

    public @NonNull ClientLevel getLevel() {
        return this.level;
    }

    public void add(@NonNull Particular particular) {
        this.particulars.add(particular);
        particular.setLevel(this);
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Added particle!"));
    }

    public void tick(boolean isLocalLevel) {
        for (var particular : this.particulars) {
            particular.tick(this.level, isLocalLevel);
        }

        this.particulars.removeIf(Particular::isDiscarded);
    }

    public void submitRender(@NonNull LevelRenderContext context) {
        for (var particular : this.particulars) {
            particular.submitRender(context);
        }
    }

    public void extractRenderState(@NonNull LevelExtractionContext context) {
        for (var particular : this.particulars) {
            particular.extractRenderState(context);
        }
    }
}
