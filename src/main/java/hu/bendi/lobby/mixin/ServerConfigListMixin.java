package hu.bendi.lobby.mixin;

import net.minecraft.server.ServerConfigList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerConfigList.class)
public class ServerConfigListMixin {
    @Inject(method = "load", at = @At("HEAD"), cancellable = true)
    private void load(CallbackInfo ci) {
        ci.cancel();
    }
}
