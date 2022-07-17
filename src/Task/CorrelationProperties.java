package Task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

public class CorrelationProperties {
    private BufferedImage image;

    public CorrelationProperties(BufferedImage image) {
        this.image = image;

    }

    public double getGorrelation(char color1, char color2) {
        double mw1 = mathWaiting(color1);
        double mw2 = mathWaiting(color2);
        return correlation(color1, mw1, deviation(mw1, color1), color2, mw2, deviation(mw2, color2));
    }

    public double mathWaiting(char color) {
        double result = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                switch (color) {
                    case ('r'): {
                        result += new Color(image.getRGB(x, y)).getRed();
                        break;
                    }
                    case ('g'): {
                        result += new Color(image.getRGB(x, y)).getGreen();
                        break;
                    }
                    case ('b'): {
                        result += new Color(image.getRGB(x, y)).getBlue();
                        break;
                    }
                }
            }
        }
        return result / (image.getWidth() * image.getHeight());
    }

    public double mathWaiting(BufferedImage image, char color) {
        double result = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                switch (color) {
                    case ('r'): {
                        result += new Color(image.getRGB(x, y)).getRed();
                        break;
                    }
                    case ('g'): {
                        result += new Color(image.getRGB(x, y)).getGreen();
                        break;
                    }
                    case ('b'): {
                        result += new Color(image.getRGB(x, y)).getBlue();
                        break;
                    }
                }
            }
        }
        return result / (image.getWidth() * image.getHeight());
    }

    public double deviation(double M, char color) {
        double result = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                switch (color) {
                    case ('r'): {
                        result += Math.pow(new Color(image.getRGB(x, y)).getRed()
                                - M, 2);
                        break;
                    }
                    case ('g'): {
                        result += Math.pow(new Color(image.getRGB(x,
                                y)).getGreen() - M, 2);
                        break;
                    }
                    case ('b'): {
                        result += Math.pow(new Color(image.getRGB(x, y)).getBlue()
                                - M, 2);
                        break;
                    }
                }
            }
        }
        return Math.sqrt(result / (image.getWidth() * image.getHeight() - 1));
    }

    public double deviation(BufferedImage image, double M, char color) {
        double result = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                switch (color) {
                    case ('r'): {
                        result += Math.pow(new Color(image.getRGB(x, y)).getRed()
                                - M, 2);
                        break;
                    }
                    case ('g'): {
                        result += Math.pow(new Color(image.getRGB(x,
                                y)).getGreen() - M, 2);
                        break;
                    }
                    case ('b'): {
                        result += Math.pow(new Color(image.getRGB(x, y)).getBlue()
                                - M, 2);
                        break;
                    }
                }
            }
        }
        return Math.sqrt(result / (image.getWidth() * image.getHeight() - 1));
    }

    public double correlation(char color1, double M1, double
            sigma1, char color2, double M2, double sigma2) {
        double result = 0;
        double[][] arr1 = new double[image.getWidth()][image.getHeight()];
        double[][] arr2 = new double[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                switch (color1) {
                    case ('r'): {
                        arr1[x][y] = new Color(image.getRGB(x, y)).getRed() - M1;
                        break;
                    }
                    case ('g'): {
                        arr1[x][y] = new Color(image.getRGB(x, y)).getGreen() -
                                M1;
                        break;
                    }
                    case ('b'): {
                        arr1[x][y] = new Color(image.getRGB(x, y)).getBlue() - M1;
                        break;
                    }
                }
                switch (color2) {
                    case ('r'): {
                        arr2[x][y] = new Color(image.getRGB(x, y)).getRed() - M2;
                        break;
                    }
                    case ('g'): {
                        arr2[x][y] = new Color(image.getRGB(x, y)).getGreen() -
                                M2;
                        break;
                    }
                    case ('b'): {
                        arr2[x][y] = new Color(image.getRGB(x, y)).getBlue() - M2;
                        break;
                    }
                }
                result += arr1[x][y] * arr2[x][y];
            }
        }
        return result / (image.getWidth() * image.getHeight() * sigma1 * sigma2);
    }

    public void autoCorrelation(char color1, String path) throws IOException {
        String fileName;
        Vector<Double> X = new Vector<>();
        Vector<Double> data = new Vector<>();
        for (int y = -10; y < 11; y += 5) {
            fileName = path + String.valueOf(color1) + "(" + String.valueOf(y) + ").txt";
            for (int x = (-image.getWidth() / 4); x <= image.getWidth() / 4; x += 16) {
                X.add((double) x);
                data.add(r_AA(y, x, color1, image));
            }
            toFile("x.txt", X);
            toFile(fileName, data);
            data.clear();
            X.clear();
        }
    }

    private double r_AA(int y, int x, char color, BufferedImage original) {
        int ii = 0, jj = 0;
        BufferedImage img = new BufferedImage(original.getWidth(), original.getHeight(),
                original.getType());
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                if (x >= 0) {
                    jj = (j + x) % original.getWidth();
                } else {
                    jj = (j + x + original.getWidth()) % original.getWidth();
                }
                if (y >= 0) {
                    ii = (i + y) % original.getHeight();
                } else {
                    ii = (i + y + original.getHeight()) % original.getHeight();
                }
                img.setRGB(j, i, original.getRGB(jj, ii));
            }
        }
        return correlation2(img, color, original);
    }

    private double correlation2(BufferedImage img1, char color, BufferedImage
            img2) {
        double M1 = mathWaiting(img1, color);
        double M2 = mathWaiting(img2, color);
        double sigma1 = deviation(img1, M1, color);
        double sigma2 = deviation(img2, M2, color);
        double result = 0;
        double[][] arr1 = new double[img1.getWidth()][img1.getHeight()];
        double[][] arr2 = new double[img2.getWidth()][img2.getHeight()];
        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                switch (color) {
                    case ('r'): {
                        arr1[x][y] = new Color(img1.getRGB(x, y)).getRed() - M1;
                        arr2[x][y] = new Color(img2.getRGB(x, y)).getRed() - M2;
                        break;
                    }
                    case ('g'): {
                        arr1[x][y] = new Color(img1.getRGB(x, y)).getGreen() - M1;
                        arr2[x][y] = new Color(img2.getRGB(x, y)).getGreen() - M2;
                        break;
                    }
                    case ('b'): {
                        arr1[x][y] = new Color(img1.getRGB(x, y)).getBlue() - M1;
                        arr2[x][y] = new Color(img2.getRGB(x, y)).getBlue() - M2;
                        break;
                    }
                }
                result += arr1[x][y] * arr2[x][y];
            }
        }
        return result / (img1.getWidth() * img1.getHeight() * sigma1 * sigma2);
    }

    public void toFile(String name, Vector<Double> rec) throws IOException {
        File f = new File(name);
        FileWriter fw = new FileWriter(f);
        for (Double aDouble : rec) {
            fw.append(aDouble.toString()).append("\n");
        }
        fw.close();

    }

}
