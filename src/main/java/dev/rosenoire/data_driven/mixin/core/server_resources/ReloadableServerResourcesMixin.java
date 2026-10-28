package dev.rosenoire.data_driven.mixin.core.server_resources;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.rosenoire.data_driven.core.registry_listeners.RegistryReloadListeners;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ReloadableServerResources.class)
public class ReloadableServerResourcesMixin {
    @Shadow
    @Final
    private ReloadableServerRegistries.Holder fullRegistryHolder;

    @Unique
    private List<PreparableReloadListener> reloadListeners;

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void init$create_listeners_from_factories(
            ReloadableServerRegistries.@NonNull LoadResult loadingContext,
            @NonNull FeatureFlagSet enabledFeatures,
            Commands.@NonNull CommandSelection commandSelection,
            @NonNull List<Registry.PendingTags<?>> postponedTags,
            @NonNull PermissionSet functionCompilationPermissions,
            @NonNull List<DataComponentInitializers.PendingComponents<?>> newComponents,
            @NonNull CallbackInfo ci
    ) {
        this.reloadListeners = new ArrayList<>();
        for (var listenerFactory : RegistryReloadListeners.FACTORIES) {
            this.reloadListeners.add(listenerFactory.create(this.fullRegistryHolder));
        }
    }

    @WrapMethod(method = "listeners")
    private @NonNull List<PreparableReloadListener> listeners$add_custom_listeners(
            @NonNull Operation<List<PreparableReloadListener>> original
    ) {
        var listeners = new ArrayList<>(original.call());
        if (this.reloadListeners != null) listeners.addAll(this.reloadListeners);
        return listeners;
    }
}
