package hu.bendi.lobby.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import hu.bendi.lobby.auth.AuthManager;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.world.GameMode;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class LobbyCommands {
    public static void register(CommandDispatcher<net.minecraft.server.command.ServerCommandSource> dispatcher) {
        dispatcher.register(literal("gm")
                .requires(serverCommandSource -> Permissions.check(serverCommandSource, "lobby.gm"))
                .then(literal("0").executes(context -> setGameMode(context.getSource().getPlayer(), 0)))
                .then(literal("1").executes(context -> setGameMode(context.getSource().getPlayer(), 1)))
                .then(literal("2").executes(context -> setGameMode(context.getSource().getPlayer(), 2)))
                .then(literal("3").executes(context -> setGameMode(context.getSource().getPlayer(), 3)))
                );

        //login
        dispatcher.register(literal("login")
                .requires(serverCommandSource -> {
                    try {
                        return AuthManager.INSTANCE.needToLogin(serverCommandSource.getPlayer().getUuid());
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
            .then(argument("jelszó", StringArgumentType.string()).executes(context -> {
                String pass = context.getArgument("jelszó", String.class);
                context.getSource().sendFeedback(new LiteralText(pass), false);
                return 1;
            })));
    }

    private static int setGameMode(ServerPlayerEntity playerEntity, int gm) {
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
