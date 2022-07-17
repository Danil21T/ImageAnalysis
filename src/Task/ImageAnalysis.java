package Task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageAnalysis {

    public static BufferedImage RGBtoYCbCr(BufferedImage original, String mode) {
        BufferedImage result = new BufferedImage(original.getWidth(),
                original.getHeight(), original.getType());
        int Y, Cb, Cr;
        Color color;
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                color = new Color(original.getRGB(x, y));
                Y = saturation((int) (0.299 * color.getRed() + 0.587 * color.getGreen()
                        + 0.114 * color.getBlue()));
                Cb = saturation((int) (0.5643 * (color.getBlue() - Y) + 128));
                Cr = saturation((int) (0.7132 * (color.getRed() - Y) + 128));
                switch (mode) {
                    case "none" : {result.setRGB(x, y, new Color(Y, Cb, Cr).getRGB()); break;}
                    case "Y" :{ result.setRGB(x, y, new Color(Y, Y, Y).getRGB());break;}
                    case "Cb" :{ result.setRGB(x, y, new Color(Cb, Cb, Cb).getRGB());break;}
                    case "Cr" :{ result.setRGB(x, y, new Color(Cr, Cr, Cr).getRGB());break;}
                }
            }
        }
        return result;
    }

    public static BufferedImage YCbCrtoRGB(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(),
                img.getType());
        int R, G, B;
        Color color;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                color = new Color(img.getRGB(x, y));
                G = saturation((int) (color.getRed() - 0.714 * (color.getBlue() - 128)
                        - 0.334 * (color.getGreen() - 128)));
                R = saturation((int) (color.getRed() + 1.402 * (color.getBlue() - 128)));
                B = saturation((int) (color.getRed() + 1.772 * (color.getGreen() -
                        128)));
                result.setRGB(x, y, new Color(R, G, B).getRGB());
            }
        }
        return result;
    }
    private static int saturation(int value) {
        if (value < 0) return 0;
        else return Math.min(value, 255);
    }


    public static double[][] decimationA(BufferedImage original, int coef, String mode)
    {
        BufferedImage img = RGBtoYCbCr(original, "none");
        double[][] result = new double[img.getHeight() / coef][img.getWidth() / coef];
        Color color;
        int x = 0, y = 0;
        switch (mode) {
            case "Cb" : {
                for (int i = 0; i < img.getHeight(); i++) {
                    y = 0;
                    for (int j = 0; j < img.getWidth(); j++) {
                        if ((j + 1) % coef == 0 && (i + 1) % coef == 0) {
                            color = new Color(img.getRGB(j, i));
                            result[x][y] = color.getGreen();
                            y++;
                            if (y == img.getWidth() / coef){
                                x++;
                            }
                        }
                    }
                }
                break;
            }
            case "Cr" : {
                for (int i = 0; i < img.getHeight(); i++) {
                    y = 0;
                    for (int j = 0; j < img.getWidth(); j++) {
                        if ((j + 1) % coef == 0 && (i + 1) % coef == 0) {
                            color = new Color(img.getRGB(j, i));
                            result[x][y] = color.getBlue();
                            y++;
                            if (y == img.getWidth() / coef){
                                x++;
                            }
                        }
                    }
                }
                break;
            }
        }
        return result;
    }
    public static double[][] decimationB(BufferedImage original, int coef, String mode)
    {
        BufferedImage img = RGBtoYCbCr(original, "none");
        double[][] result = new double[img.getHeight() / coef][img.getWidth() / coef];
        Color up, down, left, right, color;
        int x = 0, y = 0;
        switch (mode) {
            case "Cb" : {
                for (int i = 0; i < img.getHeight() / coef; i++) {
                    for (int j = 0; j < img.getWidth() / coef; j++) {
                        for (int k = i * coef; k < i * coef + coef; k++){
                            for (int p = j * coef; p < j * coef + coef; p++){
                                color = new Color(img.getRGB(p,k));
                                result[i][j] += color.getGreen();
                            }
                        }
                        result[i][j] = (int)(result[i][j] / Math.pow(coef,2));
                    }
                }
                break;
            }
            case "Cr" : {
                for (int i = 0; i < img.getHeight() / coef; i++) {
                    for (int j = 0; j < img.getWidth() / coef; j++) {
                        for (int k = i * coef; k < i * coef + coef; k++){
                            for (int p = j * coef; p < j * coef + coef; p++){
                                color = new Color(img.getRGB(p,k));
                                result[i][j] += color.getBlue();
                            }
                        }
                        result[i][j] = (int)(result[i][j] / Math.pow(coef,2));
                    }
                }
            break;
            }
        }
        return result;
    }
    public static BufferedImage recoverAfterDecimation(BufferedImage original,
                                                       double[][] Cb, double[][] Cr, int coef) {
        BufferedImage img = RGBtoYCbCr(original, "none");
        double[][] newCb = new double[img.getHeight()][img.getWidth()];
        double[][] newCr = new double[img.getHeight()][img.getWidth()];
        for (int i = 0; i < Cb.length; i++){
            for (int j = 0; j < Cb[0].length; j++){
                for (int k = i * coef; k < i * coef + coef; k++){
                    for (int l = j * coef; l < j * coef + coef; l++){
                        newCb[k][l] = Cb[i][j];
                        newCr[k][l] = Cr[i][j];
                    }
                }
            }
        }
        Color color;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                color = new Color(img.getRGB(i, j));
                img.setRGB(i, j, new Color(color.getRed(), (int) newCb[j][i], (int) newCr[j][i]).getRGB());
            }
        }
        return YCbCrtoRGB(img);
    }

    public static double PSNR(BufferedImage original, BufferedImage changed, char color)
    {
        double result = 0;
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                switch (color) {
                    case 'r' :{ result += Math.pow(new Color(original.getRGB(x,
                            y)).getRed() - new Color(changed.getRGB(x, y)).getRed(), 2);break;}
                    case 'g' :{ result += Math.pow(new Color(original.getRGB(x,
                            y)).getGreen() - new Color(changed.getRGB(x, y)).getGreen(), 2);break;}
                    case 'b' :{ result += Math.pow(new Color(original.getRGB(x,
                            y)).getBlue() - new Color(changed.getRGB(x, y)).getBlue(), 2);break;}
                }
            }
        }
        return 10 * Math.log10(original.getWidth() * original.getHeight() *
                Math.pow(Math.pow(2, 8) - 1, 2) / result);
    }



    public static void toFile(BufferedImage im, String name) throws IOException {
        File output = new File(name);
        ImageIO.write(im, "bmp", output);
    }
}
