package de.constt.ghosit_client.client.mixins;

import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public abstract class SplashTextResourceSupplierMixin {
    @Unique
    private static final Random random = new Random();
    @Unique
    private final List<String> splashes = getSplashes();

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<SplashTextRenderer> cir) {
        String splash = splashes.get(random.nextInt(splashes.size()));
        cir.setReturnValue(new SplashTextRenderer(splash));
    }

    @Unique
    private static List<String> getSplashes() {
        return List.of(
                "§bLook at §fGhosit Client §bon GitHub",
                "§aOpen-source §7on git",
                "§fUtility §7over §fAesthetics",
                "§dBuilt for §fFabric",
                "§a1.21.1 §7ready",
                "§cPackets §fdon’t §clie",
                "§eClient-side §fadvantage",
                "§7UI §fand §7control",
                "§bLatency §7is just a §bnumber",
                "§aTick-§fperfect",
                "§dModular §7by design",
                "§eHotkeys §7> §fmenus",
                "§fFeatures §7for §asure",
                "§7Debug §ffirst, §7render later",
                "§8HUDs §7are overrated",
                "§eWhy click §7when you can §fbind",
                "§cExploit §7responsibly",
                "§fClient §7logic matters",
                "§aPredictable, §7not §amagical",
                "§bGhosit Client §7is loaded §ffor sure",
                "§7Read §fthe source",
                "§eTrust, §7but §fverify",
                "§8Less glow, §7more flow",
                "§dDesigned §7for §fpower users",
                "§aPerformance §7before polish",
                "§fDeterministic §7behavior",
                "§7State §fover spam",
                "§eEvery §fpacket counts",
                "§dFabric §7maybe §fForge",
                "§bGhosit Client"
        );
    }

}