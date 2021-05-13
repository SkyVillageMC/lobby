package hu.bendi.lobby.fake;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.CriterionProgress;
import net.minecraft.network.PacketByteBuf;

import java.util.Date;

public class FakeCriterionProgress extends CriterionProgress {

    @Override
    public boolean isObtained() {
        return false;
    }

    @Override
    public void obtain() {
    }

    @Override
    public void reset() {
    }

    @Override
    public Date getObtainedDate() {
        return new Date();
    }

    @Override
    public String toString() {
        return "never";
    }

    @Override
    public void toPacket(PacketByteBuf buf) {
    }

    @Override
    public JsonElement toJson() {
        return new JsonObject();
    }
}
