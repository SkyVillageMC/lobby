package hu.bendi.lobby;

import hu.bendi.lobby.command.LobbyCommands;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.util.Identifier;

public class Lobby implements ModInitializer {

    public static final Identifier BUNGEE = new Identifier("bungeecord", "main");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> LobbyCommands.register(dispatcher));
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> Permissions.check(player, "lobby.admin"));

    }
}
