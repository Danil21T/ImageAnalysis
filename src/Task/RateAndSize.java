package Task;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class RateAndSize {

    public double[] resultR = new double[256];
    public double[] resultG = new double[256];
    public double[] resultB = new double[256];
    private BufferedImage image;

    public RateAndSize(String path, BufferedImage image) throws IOException {
        toFile(256,path+"t.txt");
        this.image = image;
    }

    public void rate(String path, String type) throws IOException {
        double size = image.getHeight()*image.getWidth();
        for(int i=0;i<image.getWidth();i++){
            for(int j =0;j<image.getHeight();j++){
                Color color = new Color(image.getRGB(i,j));
                resultR[color.getRed()]++;
                resultG[color.getGreen()]++;
                resultB[color.getBlue()]++;
            }
        }
        for(int i=0;i<256;i++){
            resultB[i]/=size;
            resultG[i]/=size;
            resultR[i]/=size;
        }
        toFile(resultB,path+"Blue.txt");
        toFile(resultG,path+"Green.txt");
        toFile(resultR,path+"Red.txt");

    }

    public double sizeOfColor(double[] data){
        double res = 0;
        for(double d : data){
            if(d!=0) {
                res += d * Math.log(d)/Math.log(2);
            }
        }
        return -res;
    }

    public void toFile(double[] data, String name) throws IOException {
        try(FileWriter fw = new FileWriter(new File(name))){
            for(double d:data) {
                fw.append(String.valueOf(d)).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void toFile(int size, String name) throws IOException {
        try(FileWriter fw = new FileWriter(new File(name))){
            for(int i=0;i<size;i++) {
                fw.append(String.valueOf(i)).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
