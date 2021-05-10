package hu.bendi.lobby.mixin;

import com.mojang.brigadier.AmbiguityConsumer;
import com.mojang.brigadier.CommandDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandDispatcher.class)
public class CommandDispatcherMixin<S> {
    @Inject(method = "findAmbiguities", at = @At("HEAD"), cancellable = true, remap = false)
    private void noConsoleSpam(AmbiguityConsumer<S> consumer, CallbackInfo ci) {
        ci.cancel();
    }
}
