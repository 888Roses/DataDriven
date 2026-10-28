package dev.rosenoire.data_driven.data.providers;

import dev.rosenoire.data_driven.shared.index.AllLang;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class DataDrivenLangProvider extends FabricLanguageProvider {
    private TranslationBuilder translationBuilder;

    public DataDrivenLangProvider(
            @NonNull FabricPackOutput packOutput,
            @NonNull CompletableFuture<HolderLookup.Provider> registryLookup
    ) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider registryLookup,
                                     @NonNull TranslationBuilder translationBuilder) {
        this.translationBuilder = translationBuilder;

        this.translationBuilder.add(
                AllLang.PARTICULAR_COMMAND_NO_PARTICLE_ERROR,
                "Could not find a particular particle at location \"%s\"!"
        );
        this.translationBuilder.add(
                AllLang.PARTICULAR_COMMAND_SUCCESS,
                "Spawning particular particle \"%s\" at location: %d, %d, %d"
        );
    }
}
