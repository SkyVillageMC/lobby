package hu.bendi.lobby.mixin;

import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ServerResourceManager.class)
public class ServerResourceManagerMixin {
    @Inject(method = "reload", at = @At("HEAD"), cancellable = true)
    private static void reload(List<ResourcePack> list, CommandManager.RegistrationEnvironment registrationEnvironment, int i, Executor executor, Executor executor2, CallbackInfoReturnable<CompletableFuture<ServerResourceManager>> cir) {
        registrationEnvironment = CommandManager.RegistrationEnvironment.INTEGRATED;
    }
}
