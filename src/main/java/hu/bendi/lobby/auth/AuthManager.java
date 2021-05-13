package hu.bendi.lobby.auth;

import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.util.*;

public class AuthManager {

    public static AuthManager INSTANCE;

    private final List<UUID> LOGGED_IN = new ArrayList<>();

    private final MinecraftServer server;

    public final Timer timer = new Timer();

    public AuthManager(MinecraftServer server) {
        this.server = server;
        INSTANCE = this;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AuthManager.INSTANCE.announce();
            }
        }, 2000, 2000);
    }

    public static void stop(MinecraftServer server) {
        AuthManager.INSTANCE.timer.cancel();
    }

    public void announce() {
        LOGGED_IN.forEach(u -> Objects.requireNonNull(server.getPlayerManager().getPlayer(u)).sendMessage(new LiteralText("Jelentkezz be a /login parancsal!").formatted(Formatting.RED), false));
    }

    public void needToRegister(UUID player) {

    }

    public boolean needToLogin(UUID player) {
        System.out.println(player.toString() + "; " + !LOGGED_IN.contains(player));
        return !LOGGED_IN.contains(player);
    }
}
