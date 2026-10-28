package dev.rosenoire.data_driven.shared;

import dev.rosenoire.data_driven.shared.index.AllLogicProviders;
import dev.rosenoire.data_driven.shared.index.AllNumericalProviders;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class DataDriven implements ModInitializer {
    public static final String NAMESPACE = "data_driven";


    public static @NonNull Identifier id(@NonNull String localPath) {
        return Identifier.fromNamespaceAndPath(NAMESPACE, localPath);
    }

    @Override
    public void onInitialize() {
        AllLogicProviders.initialize();
        AllNumericalProviders.initialize();
    }
}
