package de.constt.ghosit_client.client.mixins;

import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.render.NoWheaterModule;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Biome.class)
public abstract class BiomeMixin {
    @Inject(method = "hasPrecipitation", at = @At("HEAD"), cancellable = true)
    void hasPrecipitation(CallbackInfoReturnable<Boolean> cir) {
        if (Objects.requireNonNull(ModuleManager.getModule(NoWheaterModule.class)).getEnabledStatus()) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}