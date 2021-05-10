package hu.bendi.lobby.mixin;

import net.minecraft.Bootstrap;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.command.EntitySelectorOptions;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.tag.RequiredTagListRegistry;
import net.minecraft.util.logging.DebugLoggerPrintStream;
import net.minecraft.util.logging.LoggerPrintStream;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.PrintStream;

@Mixin(Bootstrap.class)
public abstract class BootstrapMixin {

    @Shadow private static boolean initialized;

    //@Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private static void initialize(CallbackInfo ci) {
        if (!initialized) {
            initialized = true;
            if (Registry.REGISTRIES.getIds().isEmpty()) {
                throw new IllegalStateException("Unable to load registries");
            } else {
                if (EntityType.getId(EntityType.PLAYER) == null) {
                    throw new IllegalStateException("Failed loading EntityTypes");
                } else {
                    ArgumentTypes.register();
                    RequiredTagListRegistry.validateRegistrations();
                }
            }
        }
        ci.cancel();
    }

    @Inject(method = "logMissing", at = @At("HEAD"), cancellable = true)
    private static void logMissing(CallbackInfo ci) {
        ci.cancel();
    }
}
