package hu.bendi.lobby.mixin;

import net.minecraft.advancement.criterion.ItemUsedOnBlockCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Redirect(method = "interactBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/ItemUsedOnBlockCriterion;test(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V"))
    public void ignore(ItemUsedOnBlockCriterion itemUsedOnBlockCriterion, ServerPlayerEntity player, BlockPos pos, ItemStack stack) {

    }
}
