package de.constt.ghosit_client.client.mixins;

import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
    /**
     * Make models be able to change packets
     * @param packet    the packet to modify
     * @param callbacks the callbacks for the packet
     * @param flush     should the function flush all the packets
     * @param ci        the callback info from the injection
     */
    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;Z)V", at = @At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, @Nullable PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
        ModuleManager.getModules().forEach(module -> {
            if (module.getEnabledStatus())
                if(module.modifyPacket(packet)) {
                    ci.cancel();
                }
        });
    }
}
