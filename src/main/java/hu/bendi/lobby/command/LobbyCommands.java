package hu.bendi.lobby.command;

import com.mojang.brigadier.CommandDispatcher;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import static net.minecraft.server.command.CommandManager.literal;

public class LobbyCommands {
    public static void register(CommandDispatcher dispatcher) {
        dispatcher.register(literal("gm")
                .requires(serverCommandSource -> Permissions.check(serverCommandSource, "lobby.gm"))
                .then(literal("0").executes(context -> setGamemode(context.getSource().getPlayer(), 0)))
                .then(literal("1").executes(context -> setGamemode(context.getSource().getPlayer(), 1)))
                .then(literal("2").executes(context -> setGamemode(context.getSource().getPlayer(), 2)))
                .then(literal("3").executes(context -> setGamemode(context.getSource().getPlayer(), 3)))
                );
    }

    private static int setGamemode(ServerPlayerEntity playerEntity, int gm) {
        switch (gm) {
            case 0:
                playerEntity.setGameMode(GameMode.SURVIVAL);
                break;

            case 1:
                playerEntity.setGameMode(GameMode.CREATIVE);
                break;

            case 2:
                playerEntity.setGameMode(GameMode.ADVENTURE);
                break;

            case 3:
                playerEntity.setGameMode(GameMode.SPECTATOR);
                break;
        }
        return 1;
    }
}
