package hu.bendi.lobby.fake;

import com.google.common.collect.Lists;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.criterion.CriterionProgress;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.Map;

public class FakeAdvancementProgress extends AdvancementProgress {

    @Override
    public void init(Map<String, AdvancementCriterion> criteria, String[][] requirements) {
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean isAnyObtained() {
        return false;
    }

    @Override
    public boolean obtain(String name) {
        return false;
    }

    @Override
    public boolean reset(String name) {
        return false;
    }

    @Override
    public String toString() {
        return "never gonna give you up!";
    }

    @Override
    public void toPacket(PacketByteBuf buf) {
    }

    @Nullable
    @Override
    public CriterionProgress getCriterionProgress(String name) {
        return new FakeCriterionProgress();
    }

    @Override
    public float getProgressBarPercentage() {
        return 0;
    }

    @Nullable
    @Override
    public String getProgressBarFraction() {
        return "0";
    }

    @Override
    public Iterable<String> getUnobtainedCriteria() {
        return Lists.newArrayList();
    }

    @Override
    public Iterable<String> getObtainedCriteria() {
        return Lists.newArrayList();
    }

    @Nullable
    @Override
    public Date getEarliestProgressObtainDate() {
        return new Date();
    }

    @Override
    public int compareTo(AdvancementProgress advancementProgress) {
        return 0;
    }
}
