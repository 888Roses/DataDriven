package dev.rosenoire.data_driven.client.content.particular.modules.age;

import dev.rosenoire.data_driven.core.particular.level.Particular;
import dev.rosenoire.data_driven.core.particular.level.ParticularModule;
import net.minecraft.client.multiplayer.ClientLevel;
import org.jspecify.annotations.NonNull;

public class AgeModule extends ParticularModule<AgeModuleConfig> {
    protected final int lifetime;
    protected int currentAge;

    public AgeModule(@NonNull Particular particular, @NonNull AgeModuleConfig config) {
        super(particular, config);

        this.lifetime = this.computeInt(config.lifetime());
        this.currentAge = this.lifetime;

        this.setVariable("age.lifetime", this.lifetime);
    }

    @Override
    public void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel) {
        this.tickAge();

        this.setVariable("age.age", (double) this.currentAge);
        this.setVariable("age.delta", (double) this.getAgeDelta());
    }

    protected void tickAge() {
        if (!this.compute(this.config.shouldAge())) {
            return;
        }

        var speed = this.computeInt(this.config.speed());
        if (speed > 0) this.currentAge = Math.clamp(this.currentAge - speed, 0, this.lifetime);
    }

    public float getAgeDelta() {
        return this.currentAge / (float) this.lifetime;
    }

    @Override
    public boolean isDiscarded() {
        return this.currentAge == 0;
    }
}
