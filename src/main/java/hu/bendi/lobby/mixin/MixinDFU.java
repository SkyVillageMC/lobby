package hu.bendi.lobby.mixin;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixerUpper;
import com.mojang.serialization.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DataFixerUpper.class)
public class MixinDFU<T> {

    @Inject(method = "update", at = @At("HEAD"), cancellable = true, remap = false)
    private void update(DSL.TypeReference type, Dynamic<T> input, int version, int newVersion, CallbackInfoReturnable<Dynamic<T>> cir) {
        cir.setReturnValue(input);
    }
}
