package dev.rosenoire.data_driven.client.index;

import dev.rosenoire.data_driven.core.particular.commands.ParticularCommand;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

@ApiStatus.NonExtendable
@Environment(EnvType.CLIENT)
public interface AllClientCommands {
    static void initialize() {
        command(ParticularCommand.INSTANCE);
    }

    private static <C extends ClientCommandRegistrationCallback> void command(@NonNull C command) {
        ClientCommandRegistrationCallback.EVENT.register(command);
    }
}
