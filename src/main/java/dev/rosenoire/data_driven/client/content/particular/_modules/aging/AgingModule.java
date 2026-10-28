package dev.rosenoire.data_driven.client.content.particular._modules.aging;

import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import net.minecraft.client.multiplayer.ClientLevel;
import org.jspecify.annotations.NonNull;

public class AgingModule extends ParticularModule<AgingModuleConfig> {
    protected int age;

    public AgingModule(@NonNull Particular particular, @NonNull AgingModuleConfig config) {
        super(particular, config);

        this.age = this.computeInt(config.lifetime());
    }

    @Override
    public void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel) {
        this.particular.variables.put("age", this.age);

        this.age -= Math.max(0, this.computeInt(config.agingSpeed()));

        if (this.age <= 0) {
            this.age = 0;
        }
    }

    @Override
    public boolean isDiscarded() {
        return this.age <= 0;
    }
}
