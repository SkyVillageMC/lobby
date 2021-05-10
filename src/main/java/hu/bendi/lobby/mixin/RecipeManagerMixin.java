package hu.bendi.lobby.mixin;

import java.util.Map;

import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    
    @Inject(method = "apply", at = @At("HEAD"), cancellable = true)
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "deserialize", at = @At("HEAD"), cancellable = true)
    private static void des(Identifier id, JsonObject json, CallbackInfoReturnable<Recipe<?>> cir) {
        cir.setReturnValue(null);
    }
}
