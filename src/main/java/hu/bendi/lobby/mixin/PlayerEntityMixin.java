package hu.bendi.lobby.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**
     * @author Bendi
     */
    @Overwrite
    public void incrementStat(Identifier i) {

    }

    /**
     * @author Bendi
     */
    @Overwrite
    public void increaseStat(Identifier i, int a) {

    }
}
