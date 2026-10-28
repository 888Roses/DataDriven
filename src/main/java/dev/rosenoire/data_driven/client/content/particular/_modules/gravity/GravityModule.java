package dev.rosenoire.data_driven.client.content.particular._modules.gravity;

import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import net.minecraft.client.multiplayer.ClientLevel;
import org.jspecify.annotations.NonNull;

public class GravityModule extends ParticularModule<GravityModuleConfig> {
    public GravityModule(@NonNull Particular particular, @NonNull GravityModuleConfig config) {
        super(particular, config);
    }

    @Override
    public void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel) {
        this.particular.pos = this.particular.pos.subtract(0, this.compute(this.config.gravity()), 0);
    }
}
