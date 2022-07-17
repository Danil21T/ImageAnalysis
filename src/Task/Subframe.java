package Task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Subframe {
    private BufferedImage image;
    private String path;

    public Subframe(BufferedImage img, String path) {
        image = img;
        this.path = path;
    }

    public void doSubframe(String value, int i, int j) throws IOException {
        String fileName = path + value;
        BufferedImage new_img = new BufferedImage(image.getWidth()/2, image.getHeight()/2, image.getType());
        for(int x=0;x<new_img.getWidth();x++){
            for(int y=0;y< new_img.getHeight();y++){
                int xx = (2*x+i)% image.getWidth();
                int yy = (2*y+j)%image.getHeight();
                Color c = new Color(image.getRGB(xx,yy));
                new_img.setRGB(x,y,new Color(c.getRed(),c.getRed(),c.getRed()).getRGB());
            }
        }
        toFile(fileName,new_img);
    }

    public void modification01(BufferedImage image1, BufferedImage image2) throws IOException {
            BufferedImage new_img = new BufferedImage(image1.getWidth(),image1.getHeight(),image1.getType());
            for(int i=0;i<new_img.getWidth();i++){
                for(int j=0;j<new_img.getHeight();j++){
                    Color c01 = new Color(image1.getRGB(i,j));
                    Color c00 = new Color(image2.getRGB(i,j));
                    int res = c01.getRed()-c00.getRed();
                    res = round(res);
                    new_img.setRGB(i,j,new Color(res,res,res).getRGB());
                }
            }
            toFile(path+"mod01.bmp",new_img);
    }

    public void modification10(BufferedImage image1, BufferedImage image2,BufferedImage image3) throws IOException {
        BufferedImage new_img = new BufferedImage(image3.getWidth(),image3.getHeight(),image3.getType());
        for(int i=0;i<new_img.getWidth();i++){
            for(int j=0;j<new_img.getHeight();j++){
                Color c01 = new Color(image1.getRGB(i,j));
                Color c00 = new Color(image2.getRGB(i,j));
                Color c10 = new Color(image3.getRGB(i,j));
                int res = c10.getRed()-round((c01.getRed()+c00.getRed())/2);
                res = round(res);
                new_img.setRGB(i,j,new Color(res,res,res).getRGB());
            }
        }
        toFile(path+"mod10.bmp",new_img);
    }

    public void modification11(BufferedImage image1, BufferedImage image2,BufferedImage image3, BufferedImage image4) throws IOException {
        BufferedImage new_img = new BufferedImage(image4.getWidth(),image4.getHeight(),image4.getType());
        for(int i=0;i<new_img.getWidth();i++){
            for(int j=0;j<new_img.getHeight();j++){
                Color c01 = new Color(image1.getRGB(i,j));
                Color c00 = new Color(image2.getRGB(i,j));
                Color c10 = new Color(image3.getRGB(i,j));
                Color c11 = new Color(image4.getRGB(i,j));
                int res = c11.getRed()-round((c01.getRed()+c00.getRed()+c10.getRed())/3);
                res = round(res);
                new_img.setRGB(i,j,new Color(res,res,res).getRGB());
            }
        }
        toFile(path+"mod11.bmp",new_img);
    }


    public int round(int v){
        if(v<0){
            return 0;
        }
        return Math.min(v, 255);
    }

    public void toFile(String name, BufferedImage im) throws IOException {
        File output = new File(name);
        ImageIO.write(im, "bmp", output);
    }
}
