package Task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class RateDifference {
    private BufferedImage image;
    public int[][][] first;
    public int[][][] second;
    public int[][][] third;
    public int[][][] fourth;
    public double[] resultR;
    public double[] resultG;
    public double[] resultB;

    public RateDifference(BufferedImage img, String path) throws IOException {
        image = img;
        first = new int[3][img.getWidth()][img.getHeight()];
        second = new int[3][img.getWidth()][img.getHeight()];
        third = new int[3][img.getWidth()][img.getHeight()];
        fourth = new int[3][img.getWidth()][img.getHeight()];
        toFile(512, path + "t.txt");
    }

    public void RateDifference_first_rules(String path) throws IOException {
        for (int i = 1; i < image.getWidth(); i++) {
            for (int j = 1; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                Color color_left = new Color(image.getRGB(i, j - 1));
                int res1 = color.getRed() - color_left.getRed();
                int res2 = color.getGreen() - color_left.getGreen();
                int res3 = color.getBlue() - color_left.getBlue();
                first[0][i-1][j-1] = res1;
                first[1][i-1][j-1] = res2;
                first[2][i-1][j-1] = res3;
            }
        }
        rateAndSize(first, path + "FirstRule");
    }

    public void RateDifference_second_rules(String path) throws IOException {
        for (int i = 1; i < image.getWidth(); i++) {
            for (int j = 1; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                Color color_up = new Color(image.getRGB(i - 1, j));
                int res1 = color.getRed() - color_up.getRed();
                int res2 = color.getGreen() - color_up.getGreen();
                int res3 = color.getBlue() - color_up.getBlue();
                second[0][i-1][j-1] = res1;
                second[1][i-1][j-1] = res2;
                second[2][i-1][j-1] = res3;

            }
        }
        rateAndSize(second, path + "SecondRule");
    }

    public void RateDifference_third_rules(String path) throws IOException {
        for (int i = 1; i < image.getWidth(); i++) {
            for (int j = 1; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                Color color_up_left = new Color(image.getRGB(i - 1, j - 1));
                int res1 = color.getRed() - color_up_left.getRed();
                int res2 = color.getGreen() - color_up_left.getGreen();
                int res3 = color.getBlue() - color_up_left.getBlue();
                third[0][i-1][j-1] = res1;
                third[1][i-1][j-1] = res2;
                third[2][i-1][j-1] = res3;

            }
        }
        rateAndSize(third, path + "ThirdRule");
    }

    public void RateDifference_fourth_rules(String path) throws IOException {
        for (int i = 1; i < image.getWidth(); i++) {
            for (int j = 1; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                Color color_up_left = new Color(image.getRGB(i - 1, j - 1));
                Color color_up = new Color(image.getRGB(i - 1, j));
                Color color_left = new Color(image.getRGB(i, j - 1));
                int res1 = color.getRed() -
                        (color_up_left.getRed() + color_up.getRed() + color_left.getRed()) / 3;
                int res2 = color.getGreen() -
                        (color_up_left.getGreen() + color_up.getGreen() + color_left.getGreen()) / 3;
                int res3 = color.getBlue() -
                        (color_up_left.getBlue() + color_up.getBlue() + color_left.getBlue()) / 3;
                fourth[0][i-1][j-1] = res1;
                fourth[1][i-1][j-1] = res2;
                fourth[2][i-1][j-1] = res3;

            }
        }
        rateAndSize(fourth, path + "FourthRule");
    }

    public void rateAndSize(int[][][] data, String path) throws IOException {
        resultR = new double[512];
        resultG = new double[512];
        resultB = new double[512];
        double size = data.length * data[0].length;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                int index1 = data[0][j][i]+255;
                int index2 = data[1][j][i]+255;
                int index3 = data[2][j][i]+255;
                resultR[index1]++;
                resultG[index2]++;
                resultB[index3]++;
            }
        }
        for (int i = 0; i < 512; i++) {
            resultB[i] /= size;
            resultG[i] /= size;
            resultR[i] /= size;
        }
        toFile(resultR, path + "R.txt");
        toFile(resultG, path + "G.txt");
        toFile(resultB, path + "B.txt");
    }

    public double sizeOfColor(double[] data) {
        double res = 0;
        for (double d : data) {
            if (d != 0) {
                res += d * Math.log(d)/Math.log(2);
            }
        }
        return -res;
    }


    public void toFile(double[] data, String name) throws IOException {
        try (FileWriter fw = new FileWriter(new File(name))) {
            for (double d : data) {
                fw.append(String.valueOf(d)).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void toFile(int size, String name) throws IOException {
        try (FileWriter fw = new FileWriter(new File(name))) {
            for (int i = -256; i < size/2; i++) {
                fw.append(String.valueOf(i)).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
