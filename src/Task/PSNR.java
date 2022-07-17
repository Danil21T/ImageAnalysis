package Task;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PSNR {
    private BufferedImage original;
    private BufferedImage changed;

    public PSNR(BufferedImage image, BufferedImage image_rest){
        this.original = image;
        this.changed = image_rest;
    }


    public double getPSNR(char color) {
        double result = 0;
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                switch (color) {
                    case ('r') : {
                        result += Math.pow(new Color(original.getRGB(x,
                                y)).getRed() - new Color(changed.getRGB(x, y)).getRed(), 2);
                        break;
                    }
                    case ('g') : {
                        result += Math.pow(new Color(original.getRGB(x,
                                y)).getGreen() - new Color(changed.getRGB(x, y)).getGreen(), 2);
                        break;
                    }
                    case ('b') : {
                        result += Math.pow(new Color(original.getRGB(x,
                                y)).getBlue() - new Color(changed.getRGB(x, y)).getBlue(), 2);
                        break;
                    }
                }
            }
        }
        return 10 * Math.log10(original.getWidth() * original.getHeight() *
                Math.pow(Math.pow(2, 8) - 1, 2) / result);
    }
}
