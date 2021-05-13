package hu.bendi.lobby.mixin;

import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Registry.class)
public class RegistryMixin {

    /**
     * @author Bendi
     */
    @Overwrite
    public static <T extends MutableRegistry<?>> void validate(MutableRegistry<T> registry) {

    }
}
