package hu.bendi.lobby.mixin;

import com.mojang.authlib.GameProfile;
import hu.bendi.lobby.Database;
import hu.bendi.lobby.auth.AuthManager;
import hu.bendi.lobby.fake.FakePlayerAdvancementTracker;
import hu.bendi.lobby.fake.FakeServerStatsHandler;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.xml.crypto.Data;
import java.net.SocketAddress;
import java.sql.SQLException;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Shadow @Final private MinecraftServer server;

    @Inject(method = "onPlayerConnect", at = @At("RETURN"))
    private void connect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        player.teleport(server.getOverworld(), 80.5, 123, 75.5, -90, 0);
        Database.User u;
        try {
            u = Database.getUserByName(player.getName().asString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.send(new DisconnectS2CPacket(new LiteralText("Szerverhiba történt!")));
            return;
        }
        if (u == null) {
            player.sendMessage(new LiteralText("Regisztráljá befelé!"), false);
            return;
        }
        player.sendMessage(new LiteralText(u.toString()), false);
    }

    @Inject(method = "savePlayerData", at = @At("HEAD"), cancellable = true)
    public void dontSave(ServerPlayerEntity player, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "checkCanJoin", at = @At("HEAD"), cancellable = true)
    public void allowJoin(SocketAddress address, GameProfile profile, CallbackInfoReturnable<Text> cir) {
        cir.setReturnValue(null);
    }

    /**
     * @author Bendi
     */
    @Overwrite
    public ServerPlayerEntity createPlayer(GameProfile profile) {
        ServerWorld world = server.getOverworld();
        return new ServerPlayerEntity(this.server, world, profile, new ServerPlayerInteractionManager(world));
    }

    /**
     * @author Bendi
     */
    @Overwrite
    public ServerStatHandler createStatHandler(PlayerEntity entity) {
        return FakeServerStatsHandler.NULL_STATS;
    }

    /**
     * @author Bendi
     */
    @Overwrite
    public PlayerAdvancementTracker getAdvancementTracker(ServerPlayerEntity player) {
        return new FakePlayerAdvancementTracker();
    }

    /**
     * @author Bendi
     */
    @Overwrite
    public void onDataPacksReloaded() {

    }

    @Inject(method = "respawnPlayer", at = @At("RETURN"))
    private void respawn(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
        player.teleport(server.getOverworld(), 80.5, 123, 75.5, -90, 0);
    }

    /**
     * @author Bendi
     */
    @Overwrite
    public @Nullable CompoundTag loadPlayerData(ServerPlayerEntity player) {
        return null;
    }
}
