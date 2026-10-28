package dev.rosenoire.data_driven.core.particular.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.rosenoire.data_driven.client.index.AllClientSuggestionProviders;
import dev.rosenoire.data_driven.core.particular.level.ParticularManager;
import dev.rosenoire.data_driven.core.particular.storage.ParticularConfigRegistry;
import dev.rosenoire.data_driven.shared.index.AllLang;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.commands.arguments.coordinates.*;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public final class ParticularCommand implements ClientCommandRegistrationCallback {
    public static final @NonNull ParticularCommand INSTANCE = new ParticularCommand();

    @Override
    public void register(@NonNull CommandDispatcher<FabricClientCommandSource> dispatcher,
                         @NonNull CommandBuildContext buildContext) {
        dispatcher.register(ClientCommands
                .literal("particular")
                .then(ClientCommands
                        .literal("spawn")
                        .then(ClientCommands
                                .argument("identifier", IdentifierArgument.id())
                                .suggests(SuggestionProviders.cast(AllClientSuggestionProviders.PARTICULAR_CONFIGS))
                                .then(ClientCommands
                                        .argument("pos", Vec3Argument.vec3())
                                        .executes(ParticularCommand::spawnWithContext)
                                )
                        )
                )
        );
    }

    private static int spawnWithContext(@NonNull CommandContext<FabricClientCommandSource> ctx) {
        var source = ctx.getSource();

        var identifier = ctx.getArgument("identifier", Identifier.class);
        if (!ParticularConfigRegistry.isRegistered(identifier)) {
            source.sendError(Component.translatable(AllLang.PARTICULAR_COMMAND_NO_PARTICLE_ERROR, identifier));
            return 0;
        }
        var config = ParticularConfigRegistry.get(identifier);

        var coords = ctx.getArgument("pos", Coordinates.class);
        var pos = Vec3.ZERO;
        if (coords instanceof WorldCoordinates(WorldCoordinate x, WorldCoordinate y, WorldCoordinate z)) {
            pos = new Vec3(
                    x.get(source.getPosition().x()),
                    y.get(source.getPosition().y()),
                    z.get(source.getPosition().z())
            );
        } else if (coords instanceof LocalCoordinates localCoordinates) {
            pos = localCoordinates.apply(source.getPosition(), source.getRotation());
        }

        source.sendFeedback(Component.translatable(AllLang.PARTICULAR_COMMAND_SUCCESS, identifier, pos.x, pos.y, pos.z));

        var particular = ParticularManager.add(source.getLevel(), config);
        particular.setPos(pos);

        return 1;
    }
}
