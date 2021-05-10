package hu.bendi.lobby.mixin;

import com.mojang.authlib.GameProfile;
import hu.bendi.lobby.Lobby;
import hu.bendi.lobby.fake.FakeServerRecipeBook;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerRecipeBook;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    @Shadow public ServerPlayNetworkHandler networkHandler;

    @Shadow public abstract ServerWorld getServerWorld();

    @Shadow public abstract void teleport(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch);

    private static final FakeServerRecipeBook RECIPE_BOOK = new FakeServerRecipeBook();

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "getRecipeBook", at = @At("HEAD"), cancellable = true)
    private void getRecipes(CallbackInfoReturnable<ServerRecipeBook> cir) {
        cir.setReturnValue(RECIPE_BOOK);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        if (getPos().y < 119) {
            teleport(getServerWorld(), 80.5, 123, 75.5, -90, 0);
            playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1, 10);
            return;
        }
        if (getServerWorld().getBlockState(getBlockPos()).isOf(Blocks.NETHER_PORTAL)) {
            BlockPos pos = getBlockPos();

            if (pos.getZ() < 69) {
                networkHandler.sendPacket(new CustomPayloadS2CPacket(Lobby.BUNGEE, PacketByteBufs.create().writeString("Connect\nminigame")));
            } else if (pos.getZ() < 81) {
                networkHandler.sendPacket(new CustomPayloadS2CPacket(Lobby.BUNGEE, PacketByteBufs.create().writeString("Connect\nsurvival")));
            } else {
                networkHandler.sendPacket(new CustomPayloadS2CPacket(Lobby.BUNGEE, PacketByteBufs.create().writeString("Connect\ndev")));
            }
        }
    }
}
