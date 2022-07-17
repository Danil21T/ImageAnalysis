package Task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GreyImage {

    private BufferedImage image;

    public GreyImage(BufferedImage image) {
        this.image = image;
    }

    public void doGrey(String name) throws IOException {
        BufferedImage new_im = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                double Y = 0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue();
                double Cb = 0.5643*(c.getBlue()-Y)+128;
                double Cr = 0.7132*(c.getRed()-Y)+128;
                new_im.setRGB(j, i, new Color((int) Y, (int) Cb, (int) Cr).getRGB());
            }
        }
        toFile(name + "Grey.bmp", new_im);

    }

    public void doColor(String name, BufferedImage im) throws IOException {
        BufferedImage new_im = new BufferedImage(im.getWidth(), im.getHeight(), im.getType());
        for (int i = 0; i < im.getHeight(); i++) {
            for (int j = 0; j < im.getWidth(); j++) {
                Color c = new Color(im.getRGB(j, i));
                double Y = c.getRed();
                double Cb = c.getGreen();
                double Cr = c.getBlue();
                double newGreen = Y - 0.714 * (Cr - 128) - 0.334 * (Cb - 128);
                double newRed = Y + 1.402 * (Cr - 128);
                double newBlue = Y + 1.772 * (Cb - 128);
                if (newRed < 0) newRed = 0;
                if (newRed > 255) newRed = 255;
                if (newGreen < 0) newGreen = 0;
                if (newGreen > 255) newGreen = 255;
                if (newBlue < 0) newBlue = 0;
                if (newBlue > 255) newBlue = 255;

                new_im.setRGB(j, i, new Color((int) newRed, (int) newGreen, (int) newBlue).getRGB());
            }
        }
        toFile(name + "Color.bmp", new_im);
    }

    public void correlation(String path, BufferedImage im) throws IOException {
        CorrelationProperties correlationProperties = new CorrelationProperties(im);
        System.out.println("YCb = " + correlationProperties.getGorrelation('r','g'));
        System.out.println("YCr = " + correlationProperties.getGorrelation('r','b'));
        System.out.println("CbCr = " + correlationProperties.getGorrelation('g','b'));
        correlationProperties.autoCorrelation('r',path);
        correlationProperties.autoCorrelation('g',path);
        correlationProperties.autoCorrelation('b',path);
    }

    public void separation(String path, BufferedImage im) throws IOException {
        SeparationColor separationColor = new SeparationColor(im,path);
        separationColor.separationAll();
    }

    public void toFile(String name, BufferedImage im) throws IOException {
        File output = new File(name);
        ImageIO.write(im, "bmp", output);
    }
}
