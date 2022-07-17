package Task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SeparationColor {

    private BufferedImage image;
    private String name;

    public SeparationColor(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    public void separation() throws IOException {
        toFile(name + "Red.png", sepRed());
        toFile(name + "Green.png", sepGreen());
        toFile(name + "Blue.png", sepBlue());
    }

    public void separationAll() throws IOException {
        toFile(name + "Red.png", sepAllRed());
        toFile(name + "Green.png", sepAllGreen());
        toFile(name + "Blue.png", sepAllBlue());
    }

    public BufferedImage sepRed() {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = new Color(image.getRGB(i, j));
                int red = col.getRed();
                new_im.setRGB(i, j, new Color(red, 0, 0).getRGB());
            }
        }
        return new_im;
    }

    public BufferedImage sepAllRed() {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = new Color(image.getRGB(i, j));
                int red = col.getRed();
                new_im.setRGB(i, j, new Color(red, red, red).getRGB());
            }
        }
        return new_im;
    }

    public BufferedImage sepGreen() {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = new Color(image.getRGB(i, j));
                int green = col.getGreen();
                new_im.setRGB(i, j, new Color(0, green, 0).getRGB());
            }
        }
        return new_im;
    }

    public BufferedImage sepAllGreen() {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = new Color(image.getRGB(i, j));
                int green = col.getGreen();
                new_im.setRGB(i, j, new Color(green, green, green).getRGB());
            }
        }
        return new_im;
    }

    public BufferedImage sepBlue() {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = new Color(image.getRGB(i, j));
                int blue = col.getBlue();
                new_im.setRGB(i, j, new Color(0, 0, blue).getRGB());
            }
        }
        return new_im;
    }

    public BufferedImage sepAllBlue() {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color col = new Color(image.getRGB(i, j));
                int blue = col.getBlue();
                new_im.setRGB(i, j, new Color(blue, blue, blue).getRGB());
            }
        }
        return new_im;
    }

    public void toFile(String name, BufferedImage im) throws IOException {
        File output = new File(name);
        ImageIO.write(im, "png", output);
    }
}
