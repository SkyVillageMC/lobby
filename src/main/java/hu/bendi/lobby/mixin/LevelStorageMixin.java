package hu.bendi.lobby.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.level.storage.LevelStorage;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(LevelStorage.class)
public abstract class LevelStorageMixin {
    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private static ImmutableList<String> GENERATOR_OPTION_KEYS;

    @Redirect(method = "readDataPackSettings(Ljava/io/File;Lcom/mojang/datafixers/DataFixer;)Lnet/minecraft/resource/DataPackSettings;", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/DataFixer;update(Lcom/mojang/datafixers/DSL$TypeReference;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;"))
    private static Dynamic<?> avoidDataFixing_method_29583(DataFixer dataFixer, DSL.TypeReference type, Dynamic<?> input, int version, int newVersion) {
        return input;
    }

    /**
     * @reason Avoid datafixing and return the provided dynamic
     * @author SuperCoder79
     */
    @Overwrite
    private static Pair<GeneratorOptions, Lifecycle> readGeneratorProperties(Dynamic<?> dynamic, DataFixer dataFixer, int i) {
        Dynamic<?> dynamic2 = dynamic.get("WorldGenSettings").orElseEmptyMap();
        UnmodifiableIterator var4 = GENERATOR_OPTION_KEYS.iterator();

        while(var4.hasNext()) {
            String string = (String)var4.next();
            Optional<? extends Dynamic<?>> optional = dynamic.get(string).result();
            if (optional.isPresent()) {
                dynamic2 = dynamic2.set(string, (Dynamic)optional.get());
            }
        }

        Dynamic<?> dynamic3 = dynamic2;
        DataResult<GeneratorOptions> dataResult = GeneratorOptions.CODEC.parse(dynamic3);
        final Logger var10002 = LOGGER;
        return Pair.of(dataResult.resultOrPartial(Util.method_29188("WorldGenSettings: ", var10002::error)).orElseGet(() -> {
            DataResult var10000 = RegistryLookupCodec.of(Registry.DIMENSION_TYPE_KEY).codec().parse(dynamic3);
            Registry<DimensionType> registry = null;
            try {
                registry = (Registry)var10000.resultOrPartial(Util.method_29188("Dimension type registry: ", var10002::error)).orElseThrow(() -> new IllegalStateException("Failed to get dimension registry"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            var10000 = RegistryLookupCodec.of(Registry.BIOME_KEY).codec().parse(dynamic3);
            Registry<Biome> registry2 = null;
            try {
                registry2 = (Registry)var10000.resultOrPartial(Util.method_29188("Biome registry: ", var10002::error)).orElseThrow(() -> new IllegalStateException("Failed to get biome registry"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            var10000 = RegistryLookupCodec.of(Registry.NOISE_SETTINGS_WORLDGEN).codec().parse(dynamic3);
            Registry<ChunkGeneratorSettings> registry3 = null;
            try {
                registry3 = (Registry)var10000.resultOrPartial(Util.method_29188("Noise settings registry: ", var10002::error)).orElseThrow(() -> new IllegalStateException("Failed to get noise settings registry"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return GeneratorOptions.getDefaultOptions(registry, registry2, registry3);
        }), dataResult.lifecycle());
    }

    private static DataPackSettings method_29580(Dynamic<?> dynamic) {
        DataResult var10000 = DataPackSettings.CODEC.parse(dynamic);
        Logger var10001 = LOGGER;
        var10001.getClass();
        return (DataPackSettings)var10000.resultOrPartial(var10001::error).orElse(DataPackSettings.SAFE_MODE);
    }
}
