package hu.bendi.lobby.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Criteria.class)
public class CriteriaMixin {
    /**
     * @author Bendi
     */
    @Overwrite
    private static <T extends Criterion<?>> T register(T object) {
        return null;
    }
}
