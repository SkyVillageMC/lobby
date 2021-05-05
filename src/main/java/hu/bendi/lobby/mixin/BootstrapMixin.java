package hu.bendi.lobby.mixin;

import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public abstract class BootstrapMixin {

    @Shadow
    private static void setOutputStreams() {
    }

    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private static void initialize(CallbackInfo ci) {
        setOutputStreams();
        ci.cancel();
    }

    @Inject(method = "logMissing", at = @At("HEAD"), cancellable = true)
    private static void logMissing(CallbackInfo ci) {
        ci.cancel();
    }
}
