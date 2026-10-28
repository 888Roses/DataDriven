package dev.rosenoire.data_driven.core.particular.level;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.rosenoire.data_driven.core.particular.storage.modules.ParticularModuleConfig;
import dev.rosenoire.data_driven.core.providers.ValueProvider;
import dev.rosenoire.data_driven.core.providers.numerical.NumericalProvider;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.util.Mth;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;

public abstract class ParticularModule<C extends ParticularModuleConfig> {
    public final @NonNull Particular particular;
    public final @NonNull C config;

    public ParticularModule(@NonNull Particular particular, @NonNull C config) {
        this.particular = particular;
        this.config = config;
    }

    public abstract void tick(@NonNull ClientLevel clientLevel, boolean isLocalLevel);

    public boolean isDiscarded() {
        return false;
    }

    public void submitRender(@NonNull PoseStack stack,
                             @NonNull SubmitNodeCollector collector,
                             @NonNull LevelRenderContext context) {
    }

    public void extractRenderState(@NonNull LevelExtractionContext context) {
    }

    public @NonNull ParticularLevel getLevel() {
        return this.particular.getLevel();
    }

    public @NonNull ClientLevel getClientLevel() {
        return this.getLevel().level;
    }

    public <T, P extends ValueProvider<T>> T compute(@NonNull P provider) {
        return provider.compute(this.particular.createProviderContext());
    }

    public <P extends NumericalProvider> int computeInt(@NonNull P provider) {
        return Mth.ceil(provider.compute(this.particular.createProviderContext()));
    }

    public <T> void setVariable(@NonNull String name, @NonNull T value) {
        this.particular.setVariable(name, value);
    }
}
