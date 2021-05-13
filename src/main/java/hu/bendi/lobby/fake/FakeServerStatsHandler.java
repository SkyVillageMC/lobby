package hu.bendi.lobby.fake;

import com.mojang.datafixers.DataFixer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;

import java.io.File;

public class FakeServerStatsHandler extends ServerStatHandler {

    public static final FakeServerStatsHandler NULL_STATS = new FakeServerStatsHandler();

    public FakeServerStatsHandler() {
        super(null, new File("./"));
    }

    @Override
    public void save() {

    }

    @Override
    public void setStat(PlayerEntity player, Stat<?> stat, int value) {

    }

    @Override
    public void parse(DataFixer dataFixer, String json) {

    }

    @Override
    protected String asString() {
        return "Never gonna give you up!";
    }

    @Override
    public void updateStatSet() {
    }

    @Override
    public void sendStats(ServerPlayerEntity player) {
    }

    @Override
    public void increaseStat(PlayerEntity player, Stat<?> stat, int value) {
    }

    @Override
    public <T> int getStat(StatType<T> type, T stat) {
        return 0;
    }

    @Override
    public int getStat(Stat<?> stat) {
        return 0;
    }
}
