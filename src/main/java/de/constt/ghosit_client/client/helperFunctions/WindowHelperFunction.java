package de.constt.ghosit_client.client.helperFunctions;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class WindowHelperFunction {
    public static void setWindowIcon(String pathInJar) throws IOException {
        try (
                InputStream inputStream = MinecraftClient.class.getResourceAsStream(pathInJar);
                MemoryStack stack = MemoryStack.stackPush()
        ) {
            if (inputStream == null) throw new IOException("missing: " + pathInJar);
            NativeImage image = NativeImage.read(inputStream);
            ByteBuffer pixelbuffer = MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4);
            pixelbuffer.asIntBuffer().put(image.copyPixelsRgba());

            GLFWImage.Buffer buf = GLFWImage.malloc(1, stack);
            buf.position(0);
            buf.width(image.getWidth());
            buf.height(image.getHeight());
            buf.pixels(pixelbuffer);

            GLFW.glfwSetWindowIcon(MinecraftClient.getInstance().getWindow().getHandle(), buf.position(0));
            image.close();
            MemoryUtil.memFree(pixelbuffer);
        }
    }
}
