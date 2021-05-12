package me.shedaniel.architectury.impl.forge;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.resources.RegistryReadOps;
import net.minecraft.world.level.biome.Biome;

public class RegistryAccessingCodec extends WrappingCodec<Biome> {
    public RegistryAccessingCodec(Codec<Biome> parent) {
        super(parent);
    }
    
    @Override
    public <T> DataResult<Pair<Biome, T>> decode(DynamicOps<T> ops, T input) {
        DataResult<Pair<Biome, T>> result = super.decode(ops, input);
        modifyResult(ops, result);
        return result;
    }
    
    private <T> void modifyResult(DynamicOps<T> ops, DataResult<Pair<Biome, T>> result) {
        // This is fine, Minecraft itself does this, see RegistryLookupCodec
        if (ops instanceof RegistryReadOps) {
            // TODO Implement modification
            System.out.println(result.result().get().getFirst());
        }
    }
}
