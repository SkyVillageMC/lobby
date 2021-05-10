package hu.bendi.lobby.fake;

import com.mojang.datafixers.DataFixer;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.File;

public class FakePlayerAdvancementTracker extends PlayerAdvancementTracker {

    public FakePlayerAdvancementTracker(DataFixer dataFixer, PlayerManager playerManager, ServerAdvancementLoader serverAdvancementLoader, File file, ServerPlayerEntity serverPlayerEntity) {
        super(dataFixer, playerManager, serverAdvancementLoader, file, serverPlayerEntity);
    }

    @Override
    public void reload(ServerAdvancementLoader advancementLoader) {

    }
}
