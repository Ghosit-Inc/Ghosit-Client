package de.constt.nexsus_client.client.mixins;

import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import de.constt.nexsus_client.client.roots.modules.world.XRayModule;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow
    public abstract FluidState getFluidState();

    @Shadow public abstract Block getBlock();

    /* Jesus Module
    @Inject(method = "getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;", at = @At("HEAD"), cancellable = true)
    void getCollisionShape(BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (getFluidState().isEmpty()) return;

        var client = MinecraftClient.getInstance();
        if (client == null) return;
        if (client.options.sneakKey.isPressed()) return;

        var player = client.player;
        if (player == null) return;
        if (player.isTouchingWater()) return;

        if (ProfQuHack.isEnabled(Jesus.class)) {
            cir.setReturnValue(VoxelShapes.fullCube());
            cir.cancel();
        }
    }
    */

    // XRay Module
    @Inject(method="getLuminance", at=@At("HEAD"), cancellable = true)
    private void getLuminance(CallbackInfoReturnable<Integer> cir) {
        if (ModuleManager.isEnabled(XRayModule.class) && !XRayModule.isInteresting(this.getBlock())) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }

    @Inject(method="getAmbientOcclusionLightLevel", at=@At("HEAD"), cancellable = true)
    private void getAmbientOcclusionLightLevel(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (ModuleManager.isEnabled(XRayModule.class)) {
            cir.setReturnValue(1.0f);
            cir.cancel();
        }
    }

    @Inject(method="isOpaque", at=@At("HEAD"), cancellable = true)
    private void isOpaque(CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.isEnabled(XRayModule.class)) {
            cir.setReturnValue(XRayModule.isInteresting(this.getBlock()));
            cir.cancel();
        }
    }

    @Inject(method="getRenderType", at=@At("HEAD"), cancellable = true)
    private void getRenderType(CallbackInfoReturnable<BlockRenderType> cir) {
        if (ModuleManager.isEnabled(XRayModule.class)) {
            cir.setReturnValue(BlockRenderType.INVISIBLE);
            cir.cancel();
        }
    }
}