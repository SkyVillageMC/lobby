package hu.bendi.lobby.mixin;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftDedicatedServer.class)
public class DedicatedMinecraftServerMixin {

    @Inject(method = "convertData", at = @At("HEAD"), cancellable = true)
    public void convert(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
