package de.constt.ghosit_client.client.events.client;

import com.google.common.collect.Streams;
import de.constt.ghosit_client.client.config.GhositConfigData;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.WindowHelperFunction;
import de.constt.ghosit_client.client.roots.commands.CommandManager;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.movement.FlightModule;
import de.constt.ghosit_client.client.roots.modules.movement.ParkourModule;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.SharedConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;

import java.io.IOException;
import java.util.stream.Stream;

import static de.constt.ghosit_client.client.Ghosit_clientClient.C_LOGGER;
import static de.constt.ghosit_client.client.helperFunctions.WindowHelperFunction.setWindowIcon;

public class ClientTickEventsEvent {
    private static Vec3d lastPos = Vec3d.ZERO;

    public static void register() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            String clientVersion = GhositConfigData.getConfigValue("clientVersion");
            if (client.getWindow() != null) {
                boolean watermark = Boolean.parseBoolean(
                        GhositConfigData.getConfigValue("watermark")
                );
                if(watermark) client.getWindow().setTitle(ChatHelperFunction.getPrefix(true) +" Client "+clientVersion+" | MC "+ SharedConstants.getGameVersion().getName() + " "+MinecraftClient.getInstance().getGameVersion());
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(client ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.tick();
                })
        );

        ClientTickEvents.END_CLIENT_TICK.register(client ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.postTick();
                })
        );

        ClientTickEvents.START_CLIENT_TICK.register(client ->
                CommandManager.getCommands().forEach(command -> {
                    if (command.getEnabledStatus()) {
                        command.tick();
                    }
                })
        );

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            try {
                WindowHelperFunction.setWindowIcon("/assets/ghosit_client/textures/logo-32.png");
            } catch (IOException e) {
                C_LOGGER.error(String.valueOf(e));
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(ModuleManager.isEnabled(ParkourModule.class)) {
                MinecraftClient mc = MinecraftClient.getInstance();
                assert MinecraftClient.getInstance().player != null;

                if(!ModuleManager.isEnabled(FlightModule.class)) {
                    if(!mc.player.isOnGround() || mc.options.jumpKey.isPressed()) return;
                }

                if(mc.player.isSneaking() || mc.options.sneakKey.isPressed()) return;

                Box box = mc.player.getBoundingBox();
                Box adjustedBox = box.offset(0, -0.5, 0).expand(-0.6, 0, -0.6);

                assert mc.world != null;
                Stream<VoxelShape> blockCollisions = Streams.stream(mc.world.getBlockCollisions(mc.player, adjustedBox));

                if(blockCollisions.findAny().isPresent()) return;

                mc.player.jump();
            }
        });



    }
}
