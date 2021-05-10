package hu.bendi.lobby.mixin;

import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerNetworkIo;
import net.minecraft.world.level.storage.LevelStorage;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Iterator;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow @Nullable public abstract ServerNetworkIo getNetworkIo();

    @Shadow private PlayerManager playerManager;

    @Shadow @Final protected LevelStorage.Session session;

    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "method_31400", at = @At("HEAD"), cancellable = true)
    private void keyStuff(CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(method = "createWorlds", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z"))
    public boolean has(Iterator iterator) {
        return false;
    }

    @Inject(method = "shutdown", at = @At("HEAD"), cancellable = true)
    private void shutdown(CallbackInfo ci) {
        LOGGER.info("Stopping server");
        if (this.getNetworkIo() != null) {
            this.getNetworkIo().stop();
        }

        if (this.playerManager != null) {
            this.playerManager.disconnectAllPlayers();
        }

        try {
            this.session.close();
        } catch (IOException var4) {
            LOGGER.error("Failed to unlock level {}", this.session.getDirectoryName(), var4);
        }
        ci.cancel();
    }

    @Inject(method = "convertLevel", at = @At("HEAD"), cancellable = true)
    private static void conver(LevelStorage.Session session, CallbackInfo ci) {
        ci.cancel();
    }
}
