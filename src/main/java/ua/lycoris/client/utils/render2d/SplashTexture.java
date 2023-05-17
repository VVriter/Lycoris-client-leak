package ua.lycoris.client.utils.render2d;

import java.nio.*;
import net.minecraft.util.*;
import javax.annotation.*;
import javax.imageio.*;
import java.awt.image.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.client.*;
import net.minecraftforge.fml.common.*;
import org.apache.commons.compress.utils.*;
import javax.imageio.stream.*;
import java.util.*;
import java.io.*;
import net.minecraft.client.resources.*;
import org.lwjgl.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.asm.*;
import ua.lycoris.client.asm.mixins.accessor.ISplashProgress;

public class SplashTexture
{
    private final int name;
    private final int width;
    private final int height;
    private final int frames;
    private final int size;
    private static final IntBuffer buf;
    private static final IResourcePack mcPack;
    private static final IResourcePack fmlPack;
    private static IResourcePack miscPack;

    public SplashTexture(final String location, @Nullable final ResourceLocation fallback) {
        SplashTexture.miscPack = ISplashProgress.getMiscPack();
        InputStream s = null;
        try {
            s = open(location, fallback);
            final ImageInputStream stream = ImageIO.createImageInputStream(s);
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);
            if (!readers.hasNext()) {
                throw new IOException("No suitable reader found for image" + location);
            }
            final ImageReader reader = readers.next();
            reader.setInput(stream);
            int frames = reader.getNumImages(true);
            BufferedImage[] images = new BufferedImage[frames];
            for (int i = 0; i < frames; ++i) {
                images[i] = reader.read(i);
            }
            reader.dispose();
            this.width = images[0].getWidth();
            int height = images[0].getHeight();
            if (height > this.width && height % this.width == 0) {
                frames = height / this.width;
                final BufferedImage original = images[0];
                height = this.width;
                images = new BufferedImage[frames];
                for (int j = 0; j < frames; ++j) {
                    images[j] = original.getSubimage(0, j * height, this.width, height);
                }
            }
            this.frames = frames;
            this.height = height;
            int size;
            for (size = 1; size / this.width * (size / height) < frames; size *= 2) {}
            this.size = size;
            GL11.glEnable(3553);
            synchronized (SplashProgress.class) {
                GL11.glBindTexture(3553, this.name = GL11.glGenTextures());
            }
            GL11.glTexParameteri(3553, 10241, 9728);
            GL11.glTexParameteri(3553, 10240, 9728);
            GL11.glTexImage2D(3553, 0, 6408, size, size, 0, 32993, 33639, (IntBuffer)null);
            SplashProgress.checkGLError("Texture creation");
            for (int j = 0; j * (size / this.width) < frames; ++j) {
                for (int k = 0; j * (size / this.width) + k < frames && k < size / this.width; ++k) {
                    SplashTexture.buf.clear();
                    final BufferedImage image = images[j * (size / this.width) + k];
                    for (int l = 0; l < height; ++l) {
                        for (int m = 0; m < this.width; ++m) {
                            SplashTexture.buf.put(image.getRGB(m, l));
                        }
                    }
                    SplashTexture.buf.position(0).limit(this.width * height);
                    GL11.glTexSubImage2D(3553, 0, k * this.width, j * height, this.width, height, 32993, 33639, SplashTexture.buf);
                    SplashProgress.checkGLError("Texture uploading");
                }
            }
            GL11.glBindTexture(3553, 0);
            GL11.glDisable(3553);
        }
        catch (Exception e) {
            FMLLog.log.error("Error reading texture from file: {}", (Object)location, (Object)e);
            throw new RuntimeException(e);
        }
        finally {
            IOUtils.closeQuietly((Closeable)s);
        }
    }

    public int getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getFrames() {
        return this.frames;
    }

    public int getSize() {
        return this.size;
    }

    public void bind() {
        GL11.glBindTexture(3553, this.name);
    }

    public void delete() {
        GL11.glDeleteTextures(this.name);
    }

    public float getU(final int frame, final float u) {
        return this.width * (frame % (this.size / this.width) + u) / this.size;
    }

    public float getV(final int frame, final float v) {
        return this.height * (frame / (this.size / this.width) + v) / this.size;
    }

    public void texCoord(final int frame, final float u, final float v) {
        GL11.glTexCoord2f(this.getU(frame, u), this.getV(frame, v));
    }

    private static InputStream open(final String loc, @Nullable final ResourceLocation fallback) throws IOException {
        return loc.getClass().getResourceAsStream(loc);
    }

    private static IResourcePack createResourcePack(final File file) {
        if (file.isDirectory()) {
            return (IResourcePack)new FolderResourcePack(file);
        }
        return (IResourcePack)new FileResourcePack(file);
    }

    static {
        buf = BufferUtils.createIntBuffer(4194304);
        mcPack = (IResourcePack)Minecraft.getMinecraft().defaultResourcePack;
        fmlPack = createResourcePack(FMLSanityChecker.fmlLocation);
    }
}