package de.constt.ghosit_client.client.mixins;

import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.render.NoWheaterModule;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(World.class)
public abstract class WorldMixin {
    @Inject(method = "getRainGradient", at = @At("HEAD"), cancellable = true)
    private void getRainGradient(float delta, CallbackInfoReturnable<Float> cir) {
        if (Objects.requireNonNull(ModuleManager.getModule(NoWheaterModule.class)).getEnabledStatus()) {
            cir.setReturnValue(0.0f);
            cir.cancel();
        }
    }
}
