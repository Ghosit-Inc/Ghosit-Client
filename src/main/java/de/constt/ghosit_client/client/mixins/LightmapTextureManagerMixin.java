package de.constt.ghosit_client.client.mixins;

import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.render.FullbrightModule;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureManagerMixin {
    @Inject(method = "getBrightness", at=@At("HEAD"), cancellable = true)
    private static void getBrightness(DimensionType type, int lightLevel, CallbackInfoReturnable<Float> cir) {
        if (Objects.requireNonNull(ModuleManager.getModule(FullbrightModule.class)).getEnabledStatus()) {
            cir.setReturnValue(1f);
            cir.cancel();
        }
    }
}