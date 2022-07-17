package Task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Decimation {

    public void toFile(String name, BufferedImage im) throws IOException {
        File output = new File(name);
        ImageIO.write(im, "png", output);
    }

    public void decimation_first(int k, BufferedImage image, String path) throws IOException {
        BufferedImage img = new BufferedImage(image.getWidth() / k, image.getHeight() / k, image.getType());
        Color color;
        int x = 0, y;

        for (int i = 0; i < image.getHeight(); i++) {
            y = 0;
            for (int j = 0; j < image.getWidth(); j++) {
                if ((j + 1) % k == 0 && (i + 1) % k == 0) {
                    color = new Color(image.getRGB(j, i));
                    img.setRGB(y, x, color.getRGB());
                    y++;
                    if (y == image.getWidth() / k) {
                        x++;
                    }
                }
            }
        }

        toFile(path + k + "DECIM_first.png", img);

    }

    public void decimation_second(int k, BufferedImage image, String path) throws IOException {
        BufferedImage img = new BufferedImage(image.getWidth() / k, image.getHeight() / k, image.getType());

        Color color;

        for (int i = 0; i < image.getHeight() / k; i++) {
            for (int j = 0; j < image.getWidth() / k; j++) {
                double result1 = 0;
                double result2 = 0;
                for (int l = i * k; l < i * k + k; l++) {
                    for (int p = j * k; p < j * k + k; p++) {
                        color = new Color(image.getRGB(p, l));
                        result1 += color.getGreen();
                        result2 += color.getBlue();
                    }
                }
                img.setRGB(j,i,new Color(new Color(image.getRGB(j*k,i*k)).getRed(),
                        (int)(result1/Math.pow(k,2)),(int)(result2/Math.pow(k,2))).getRGB());
            }
        }
        toFile(path + k + "DECIM_second.png", img);

    }

    public void doRGB(BufferedImage image, BufferedImage compressed_img, int k, String type) throws IOException {
        BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
        double[][] newCb = new double[img.getWidth()][img.getHeight()];
        double[][] newCr = new double[img.getWidth()][img.getHeight()];
        //double[][] newY = new double[img.getWidth()][img.getHeight()];

        for (int i = 0; i < compressed_img.getWidth(); i++) {
            for (int j = 0; j < compressed_img.getHeight(); j++) {
                for (int p = i * k; p < i * k + k; p++) {
                    for (int l = j * k; l < j * k + k; l++) {
                        Color color = new Color(compressed_img.getRGB(i,j));
                        newCb[p][l] = color.getGreen();
                        newCr[p][l] = color.getBlue();
                        //newY[p][l] = color.getRed();
                    }
                }
            }
        }

        Color color;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                color = new Color(image.getRGB(i, j));
                img.setRGB(i, j, new Color(color.getRed(), (int) newCb[i][j], (int) newCr[i][j]).getRGB());
            }
        }

        GreyImage new_im = new GreyImage(image);

        new_im.doColor("GreyImage/Decimation/Recovery_imageRGB"+k+type+".png", img);
        toFile("GreyImage/Decimation/Recovery_imageYCbCr"+k+type+".png",img);

    }
}
