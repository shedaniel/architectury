package me.shedaniel.architectury.mixin.forge;

import com.mojang.serialization.Codec;
import me.shedaniel.architectury.impl.forge.RegistryAccessingCodec;
import net.minecraft.resources.RegistryReadOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Biome.class)
public class MixinBiome {
    @Mutable
    @Shadow
    @Final
    public static Codec<Biome> DIRECT_CODEC;
    
    /**
     * Wraps and modifies the biome codec to post "modify" the biome,
     * forge's {@link BiomeLoadingEvent} is invoked at the apply stage,
     * but does not provide the necessary information contained by {@link RegistryReadOps}
     */
    @Inject(method = "<clinit>", at = @At(value = "INVOKE",
                                          target = "Lnet/minecraft/resources/RegistryFileCodec;homogeneousList(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;"))
    private static void modifyCodec(CallbackInfo info) {
        DIRECT_CODEC = new RegistryAccessingCodec(DIRECT_CODEC);
    }
}
