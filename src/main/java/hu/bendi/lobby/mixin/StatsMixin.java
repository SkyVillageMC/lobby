package hu.bendi.lobby.mixin;

import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Stats.class)
public class StatsMixin {
    /**
     * @author Bendi
     */
    @Overwrite
    private static <T> StatType<T> registerType(String string, Registry<T> registry) {
        return null;
    }

    private static Identifier NIL = new Identifier("never");

    /**
     * @author Bendi
     */
    @Overwrite
    private static Identifier register(String string, StatFormatter statFormatter) {
        return NIL;
    }
}
