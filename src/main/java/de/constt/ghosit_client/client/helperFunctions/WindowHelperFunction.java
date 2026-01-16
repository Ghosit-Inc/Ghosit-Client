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

            ByteBuffer pixelBuffer = MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4);
            pixelBuffer.asIntBuffer().put(image.copyPixelsAbgr());
            pixelBuffer.flip();

            GLFWImage.Buffer buffer = GLFWImage.malloc(1, stack);
            buffer.width(image.getWidth());
            buffer.height(image.getHeight());
            buffer.pixels(pixelBuffer);

            GLFW.glfwSetWindowIcon(
                    MinecraftClient.getInstance().getWindow().getHandle(),
                    buffer
            );

            image.close();
            MemoryUtil.memFree(pixelBuffer);
        }
    }

}
