package hu.bendi.lobby.mixin;

import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {
    /**
     * @author Bendi
     */
    @Overwrite
    public Stat<Identifier> getOpenStat() {
        return null;
    }

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PiglinBrain;onGuardedBlockInteracted(Lnet/minecraft/entity/player/PlayerEntity;Z)V"))
    public void noAI(PlayerEntity player, boolean blockOpen) {}
}
