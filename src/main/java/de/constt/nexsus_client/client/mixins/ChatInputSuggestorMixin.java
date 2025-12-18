package de.constt.nexsus_client.client.mixins;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.suggestion.Suggestions;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.CommandDispatcher;

import java.util.concurrent.CompletableFuture;

@Mixin(ChatInputSuggestor.class)
public abstract class ChatInputSuggestorMixin {
    @Shadow @Final TextFieldWidget textField;
    @Shadow private ParseResults<?> parse;
    @Shadow private CompletableFuture<Suggestions> pendingSuggestions;
    @Shadow private boolean completingSuggestions;
    @Shadow private ChatInputSuggestor.SuggestionWindow window;

    @Shadow protected abstract void showCommandSuggestions();

    @Inject(method = "refresh", at = @At("HEAD"), cancellable = true)
    private void injectStarSuggestion(CallbackInfo ci) {
        String text = textField.getText();
        if (!"*".equals(text)) return;

        SuggestionsBuilder builder = new SuggestionsBuilder(text, 0);
        builder.suggest("*help");
        builder.suggest("*bind");

        this.pendingSuggestions = CompletableFuture.completedFuture(builder.build());

        if (this.parse == null) {
            CommandDispatcher<Object> dispatcher = new CommandDispatcher<>();
            dispatcher.register(LiteralArgumentBuilder.literal("*help"));
            this.parse = dispatcher.parse(new StringReader(text), new Object());
        }

        if (this.window == null || !this.completingSuggestions) {
            this.showCommandSuggestions();
        }

        ci.cancel();
    }
}
