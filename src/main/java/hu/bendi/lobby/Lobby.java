package hu.bendi.lobby;

import hu.bendi.lobby.auth.AuthManager;
import hu.bendi.lobby.command.LobbyCommands;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.sql.SQLException;

public class Lobby implements ModInitializer {

    public static final Identifier BUNGEE = new Identifier("bungeecord", "main");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> LobbyCommands.register(dispatcher));
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> Permissions.check(player, "lobby.admin"));
        ServerLifecycleEvents.SERVER_STARTED.register(AuthManager::new);
        ServerLifecycleEvents.SERVER_STOPPING.register(AuthManager::stop);
        try {
            Database.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void onCollide(ServerPlayerEntity player, BlockState block) {

    }
}
