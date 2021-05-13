package hu.bendi.lobby.mixin;

import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ConfiguredSurfaceBuilders.class)
public class ConfiguredSurfaceBuildersMixin {
    /**
     * @author Bendi
     */
    @Overwrite
    private static <SC extends SurfaceConfig> ConfiguredSurfaceBuilder<SC> register(String id, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        return null;
    }
}
