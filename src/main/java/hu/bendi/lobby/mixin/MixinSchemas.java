package hu.bendi.lobby.mixin;

import com.mojang.datafixers.DataFixerBuilder;
import net.minecraft.datafixer.Schemas;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Schemas.class)
public class MixinSchemas {
    @Inject(method = "build",at = @At("HEAD"), cancellable = true)
    private static void build(DataFixerBuilder builder, CallbackInfo ci) {
        ci.cancel();
    }
}
