package hu.bendi.lobby.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import net.minecraft.world.storage.VersionedChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(VersionedChunkStorage.class)
public class VersionedChunkStorageMixin {
    @Inject(method = "updateChunkTag", at = @At("HEAD"), cancellable = true)
    private void update(RegistryKey<World> registryKey, Supplier<PersistentStateManager> persistentStateManagerFactory, CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        cir.setReturnValue(tag);
    }
}
