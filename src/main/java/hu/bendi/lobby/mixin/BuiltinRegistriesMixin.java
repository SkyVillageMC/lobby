package hu.bendi.lobby.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(BuiltinRegistries.class)
public class BuiltinRegistriesMixin {
    @Inject(method = "addRegistry(Lnet/minecraft/util/registry/RegistryKey;Lcom/mojang/serialization/Lifecycle;Ljava/util/function/Supplier;)Lnet/minecraft/util/registry/Registry;", at = @At("HEAD"), cancellable = true)
    private static void no(RegistryKey<? extends Registry<?>> registryRef, Lifecycle lifecycle, Supplier<?> defaultValueSupplier, CallbackInfoReturnable<Registry<?>> cir) {
        if (registryRef != Registry.BIOME_KEY) cir.setReturnValue(null);
    }
}
