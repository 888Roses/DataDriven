package dev.rosenoire.data_driven.core.cca;

import dev.rosenoire.data_driven.shared.DataDriven;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import java.util.function.Function;

@SuppressWarnings("UnstableApiUsage")
public abstract class SmartComponent<C extends SmartComponent<C>> implements AutoSyncedComponent {
    private final @NonNull SubComponentStorage subComponentStorage = this.initializeSubComponentStorage();

    protected abstract @NonNull ComponentKey<C> getKey();

    protected abstract @NonNull Object getSynchronisationTarget();

    protected @NonNull SubComponentStorage initializeSubComponentStorage() {
        return new SubComponentStorage(this);
    }

    public @NonNull SubComponentStorage getSubComponents() {
        return this.subComponentStorage;
    }

    public <S extends SubCardinalComponent> @NonNull S addComponent(@NonNull Identifier identifier,
                                                                    @NonNull S subComponent) {
        return this.getSubComponents().add(identifier, subComponent);
    }

    public <S extends SubCardinalComponent> @NonNull S addComponentFactory(@NonNull Identifier identifier,
                                                                           @NonNull Function<C, S> constructor) {
        //noinspection unchecked
        return this.addComponent(identifier, constructor.apply((C) this));
    }

    public <S extends SubCardinalComponent> @NonNull S addComponent(@NonNull String modPath,
                                                                    @NonNull S subComponent) {
        return this.getSubComponents().add(modPath, subComponent);
    }

    public <S extends SubCardinalComponent> @NonNull S addComponentFactory(@NonNull String modPath,
                                                                           @NonNull Function<C, S> constructor) {
        return this.addComponentFactory(DataDriven.id(modPath), constructor);
    }

    public void synchronise() {
        this.getKey().sync(this.getSynchronisationTarget());
    }

    @Override
    public void readData(@NonNull ValueInput readView) {
        this.readSubComponentData(readView);
    }

    @Override
    public void writeData(@NonNull ValueOutput writeView) {
        this.writeSubComponentData(writeView);
    }

    protected void readSubComponentData(@NonNull ValueInput readView) {
        for (var subComponentEntry : this.getSubComponents().storage.entrySet()) {
            var name = subComponentEntry.getKey().toString();
            readView.child(name).ifPresent(child ->
                    subComponentEntry.getValue().readData(child)
            );
        }
    }

    protected void writeSubComponentData(@NonNull ValueOutput writeView) {
        for (var subComponentEntry : this.getSubComponents().storage.entrySet()) {
            var name = subComponentEntry.getKey().toString();
            var child = writeView.child(name);
            subComponentEntry.getValue().writeData(child);
        }
    }
}
