package me.shedaniel.architectury.impl.forge;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

public class WrappingCodec<A> implements Codec<A> {
    protected final Codec<A> parent;
    
    public WrappingCodec(Codec<A> parent) {
        this.parent = parent;
    }
    
    @Override
    public <T> DataResult<Pair<A, T>> decode(DynamicOps<T> ops, T input) {
        return this.parent.decode(ops, input);
    }
    
    @Override
    public <T> DataResult<T> encode(A input, DynamicOps<T> ops, T prefix) {
        return this.parent.encode(input, ops, prefix);
    }
}
