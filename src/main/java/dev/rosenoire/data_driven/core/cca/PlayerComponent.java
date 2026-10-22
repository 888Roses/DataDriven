package dev.rosenoire.data_driven.core.cca;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public abstract class PlayerComponent<P extends PlayerComponent<P>> extends SmartComponent<P> {
    protected final @NonNull Player player;

    public PlayerComponent(@NonNull Player player) {
        this.player = player;
    }

    public @NonNull Player player() {
        return this.player;
    }

    public @NonNull Level level() {
        return this.player().level();
    }

    public @NonNull Vec3 pos() {
        return this.player().position();
    }

    public boolean isClientSide() {
        return this.level().isClientSide();
    }

    @Override
    protected @NonNull Object getSynchronisationTarget() {
        return this.player;
    }
}
