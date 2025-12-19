package de.constt.nexsus_client.client.roots.modules.world;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;

import java.util.List;

@ModuleInfoAnnotation(
        name = "XRay",
        description = "Shows / hides set blocks",
        category = CategoryImplementation.Categories.WORLD,
        internalModuleName = "xray"
)

public class XRayModule extends ModuleImplementation {
    public static volatile XRayModule INSTANCE;

    public static final List<Block> ORES = List.of(Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE, Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE, Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE, Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE, Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE, Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE, Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE, Blocks.ANCIENT_DEBRIS);


    @Override
    public void toggle() {
        super.toggle();
        MinecraftClient.getInstance().worldRenderer.reload();
    }

    /**
     * Determines if the block {@code block} is interesting, or see able with X-ray
     * @param block the block to check
     * @return      true if the block is interesting and should be seen with X-ray
     */
    public static boolean isInteresting(Block block) {
        return ORES.contains(block);
    }
}
