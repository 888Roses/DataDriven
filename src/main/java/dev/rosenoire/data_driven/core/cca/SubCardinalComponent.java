package dev.rosenoire.data_driven.core.cca;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public abstract class SubCardinalComponent {
    public abstract void readData(@NonNull ValueInput readView);
    public abstract void writeData(@NonNull ValueOutput writeView);
}
