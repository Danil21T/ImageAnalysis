import Task.*;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("kodim04.png");
        BufferedImage image = ImageIO.read(file);
        int coef1 = 2;
        int coef2 = 4;
        File f2 = new File("C:\\Users\\Lenovo\\Desktop\\Labs\\BlockShifrSp\\bc4838e4-8ed8-4264-8681-f37403098a0a_1585636429498.jpg");
        BufferedImage original = ImageIO.read(f2);

//        светофор
//        SeparationColor separationColor = new SeparationColor(image, "RGBSeparation1\\");
//        separationColor.separation();
//
//////корреляция+графики
//        CorrelationProperties correlationProperties = new CorrelationProperties(image);
//        String path = "RGBAutocorrelation\\";
//        System.out.println("RG = " + correlationProperties.getGorrelation('r','g'));
//        System.out.println("RB = " + correlationProperties.getGorrelation('r','b'));
//        System.out.println("BG = " + correlationProperties.getGorrelation('b','g'));
//        correlationProperties.autoCorrelation('r',path);
//        correlationProperties.autoCorrelation('g',path);
//        correlationProperties.autoCorrelation('b',path);
//////
////        //Формат YCbCr
        GreyImage greyImage =new GreyImage(original);
        greyImage.doGrey("GreyImage\\");
        File file1 = new File("GreyImage\\Grey.bmp");
        BufferedImage image1 = ImageIO.read(file1);
//////
////        //RGB формат
//        greyImage.doColor("GreyImage\\",image1 );
////
////        //Корреляция
//        greyImage.correlation("GreyImage\\Correlation\\",image1);
////
////        //Светофор
        greyImage.separation("GreyImage\\", image1);
////
////        //значение шума
//        File file2 = new File("GreyImage\\Color.bmp");
//        BufferedImage image2 = ImageIO.read(file2);
//        PSNR psnr = new PSNR(original,image2);
//        System.out.println("PSNR R: " + psnr.getPSNR('r'));
//        System.out.println("PSNR G: " + psnr.getPSNR('g'));
//        System.out.println("PSNR B: " + psnr.getPSNR('b'));

////        //Децимация *2
//        Decimation decimation = new Decimation();
//        decimation.decimation_first(2, image1, "GreyImage\\Decimation\\");
//        decimation.decimation_second(2, image1, "GreyImage\\Decimation\\");
//        File grey_compressed_first = new File("GreyImage/Decimation/2DECIM_first.png");
//        File grey_compressed_second = new File("GreyImage/Decimation/2DECIM_second.png");
//        BufferedImage grey_compressed_first_img = ImageIO.read(grey_compressed_first);
//        BufferedImage grey_compressed_second_img = ImageIO.read(grey_compressed_second);
//        decimation.doRGB(image1,grey_compressed_first_img,2, "_first_");
//        decimation.doRGB(image1,grey_compressed_second_img,2, "_second_");
//        BufferedImage grey_recovery_first = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageYCbCr2_first_.png"));
//        BufferedImage grey_recovery_second = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageYCbCr2_second_.png"));
//        BufferedImage RGB_recovery_first = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageRGB2_first_.pngColor.bmp"));
//        BufferedImage RGB_recovery_second = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageRGB2_second_.pngColor.bmp"));
//
//        PSNR psnr_grey_recovery_first = new PSNR( image1,grey_recovery_first);
//        PSNR psnr_grey_recovery_second = new PSNR(image1,grey_recovery_second);
//        PSNR psnr_rgb_recovery_first = new PSNR(image,RGB_recovery_first);
//        PSNR psnr_rgb_recovery_second = new PSNR(image,RGB_recovery_second);
//
//        System.out.println("Первый способ: \nCb: "+ psnr_grey_recovery_first.getPSNR('g')
//                + "\nCr: "+ psnr_grey_recovery_first.getPSNR('b')+"\nR: "+
//                psnr_rgb_recovery_first.getPSNR('r')+"\nG: "+
//                psnr_rgb_recovery_first.getPSNR('g')+"\nB: "+
//                psnr_rgb_recovery_first.getPSNR('b'));
//        System.out.println("Второй способ: \nCb: "+ psnr_grey_recovery_second.getPSNR('g')
//                + "\nCr: "+psnr_grey_recovery_second.getPSNR('b')+"\nR: "+
//                psnr_rgb_recovery_second.getPSNR('r')+"\nG: "+
//                psnr_rgb_recovery_second.getPSNR('g')+"\nB: "+
//                psnr_rgb_recovery_second.getPSNR('b'));
//////
//////        //Децимация *4
//        Decimation decimation4 = new Decimation();
//        decimation4.decimation_first(4, image1, "GreyImage\\Decimation\\");
//        decimation4.decimation_second(4, image1, "GreyImage\\Decimation\\");
//        File grey_compressed_first4 = new File("GreyImage/Decimation/4DECIM_first.png");
//        File grey_compressed_second4 = new File("GreyImage/Decimation/4DECIM_second.png");
//        BufferedImage grey_compressed_first_img4 = ImageIO.read(grey_compressed_first4);
//        BufferedImage grey_compressed_second_img4 = ImageIO.read(grey_compressed_second4);
//        decimation4.doRGB(image1,grey_compressed_first_img4,4, "_first_");
//        decimation4.doRGB(image1,grey_compressed_second_img4,4, "_second_");
//        BufferedImage grey_recovery_first4 = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageYCbCr4_first_.png"));
//        BufferedImage grey_recovery_second4 = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageYCbCr4_second_.png"));
//        BufferedImage RGB_recovery_first4 = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageRGB4_first_.pngColor.bmp"));
//        BufferedImage RGB_recovery_second4 = ImageIO.read(new File("GreyImage/Decimation/Recovery_imageRGB4_second_.pngColor.bmp"));
//
//        PSNR psnr_grey_recovery_first4 = new PSNR( image1,grey_recovery_first4);
//        PSNR psnr_grey_recovery_second4 = new PSNR(image1,grey_recovery_second4);
//        PSNR psnr_rgb_recovery_first4 = new PSNR(image,RGB_recovery_first4);
//        PSNR psnr_rgb_recovery_second4 = new PSNR(image,RGB_recovery_second4);
//
//        System.out.println("Первый способ: \nCb: "+ psnr_grey_recovery_first4.getPSNR('g')
//                + "\nCr: "+ psnr_grey_recovery_first4.getPSNR('b')+"\nR: "+
//                psnr_rgb_recovery_first4.getPSNR('r')+"\nG: "+
//                psnr_rgb_recovery_first4.getPSNR('g')+"\nB: "+
//                psnr_rgb_recovery_first4.getPSNR('b'));
//        System.out.println("Второй способ: \nCb: "+ psnr_grey_recovery_second4.getPSNR('g')
//                + "\nCr: "+psnr_grey_recovery_second4.getPSNR('b')+"\nR: "+
//                psnr_rgb_recovery_second4.getPSNR('r')+"\nG: "+
//                psnr_rgb_recovery_second4.getPSNR('g')+"\nB: "+
//                psnr_rgb_recovery_second4.getPSNR('b'));
//
//
//        String afterDecimationA2="GreyImage/Decimation/afterDecimationA2.bmp";
//        String afterDecimationB2="GreyImage/Decimation/afterDecimationB2.bmp";
//        String afterDecimationA4="GreyImage/Decimation/afterDecimationA4.bmp";
//        String afterDecimationB4="GreyImage/Decimation/afterDecimationB4.bmp";
//        String YCbCrName = "GreyImage/Grey.bmp";
//        double[][] cb=ImageAnalysis.decimationA(original,coef1,"Cb");
//        double[][] cr=ImageAnalysis.decimationA(original,coef1,"Cr");
//        ImageAnalysis.toFile(ImageAnalysis.recoverAfterDecimation(original,cb,cr,coef1),afterDecimationA2);
//        BufferedImage afterRGB =  ImageIO.read(new File(afterDecimationA2));
//        BufferedImage y =  ImageIO.read(new File(YCbCrName));
//        BufferedImage afterYCbCr=ImageAnalysis.RGBtoYCbCr(afterRGB,"none");
//        System.out.println("\ndecimation first rule coef = 2: ");
//        System.out.println("\nPSNR R: " + ImageAnalysis.PSNR(original, afterRGB, 'r'));
//        System.out.println("PSNR G: " + ImageAnalysis.PSNR(original, afterRGB, 'g'));
//        System.out.println("PSNR B: " + ImageAnalysis.PSNR(original, afterRGB, 'b'));
//        System.out.println("\nPSNR Y: " + ImageAnalysis.PSNR(y, afterYCbCr, 'r'));
//        System.out.println("PSNR Cb: " + ImageAnalysis.PSNR(y, afterYCbCr, 'g'));
//        System.out.println("PSNR Cr: " + ImageAnalysis.PSNR(y, afterYCbCr, 'b'));
//        System.out.println("\ndecimation second rule coef = 2: ");
//        cb=ImageAnalysis.decimationB(original,coef1,"Cb");
//        cr=ImageAnalysis.decimationB(original,coef1,"Cr");
//        ImageAnalysis.toFile(ImageAnalysis.recoverAfterDecimation(original,cb,cr,coef1),afterDecimationB2);
//        afterRGB =  ImageIO.read(new File(afterDecimationB2));
//        afterYCbCr=ImageAnalysis.RGBtoYCbCr(afterRGB,"none");
//        System.out.println("\nPSNR R: " + ImageAnalysis.PSNR(original, afterRGB, 'r'));
//        System.out.println("PSNR G: " + ImageAnalysis.PSNR(original, afterRGB, 'g'));
//        System.out.println("PSNR B: " + ImageAnalysis.PSNR(original, afterRGB, 'b'));
//        System.out.println("\nPSNR Y: " + ImageAnalysis.PSNR(y, afterYCbCr, 'r'));
//        System.out.println("PSNR Cb: " + ImageAnalysis.PSNR(y, afterYCbCr, 'g'));
//        System.out.println("PSNR Cr: " + ImageAnalysis.PSNR(y, afterYCbCr, 'b'));
//        cb=ImageAnalysis.decimationA(original,coef2,"Cb");
//        cr=ImageAnalysis.decimationA(original,coef2,"Cr");
//        ImageAnalysis.toFile(ImageAnalysis.recoverAfterDecimation(original,cb,cr,coef2),afterDecimationA4);
//        afterRGB =  ImageIO.read(new File(afterDecimationA4));
//        afterYCbCr=ImageAnalysis.RGBtoYCbCr(afterRGB,"none");
//        System.out.println("\ndecimation first rule coef =  4: ");
//        System.out.println("\nPSNR R: " + ImageAnalysis.PSNR(original, afterRGB, 'r'));
//        System.out.println("PSNR G: " + ImageAnalysis.PSNR(original, afterRGB, 'g'));
//        System.out.println("PSNR B: " + ImageAnalysis.PSNR(original, afterRGB, 'b'));
//        System.out.println("\nPSNR Y: " + ImageAnalysis.PSNR(y, afterYCbCr, 'r'));
//        System.out.println("PSNR Cb: " + ImageAnalysis.PSNR(y, afterYCbCr, 'g'));
//        System.out.println("PSNR Cr: " + ImageAnalysis.PSNR(y, afterYCbCr, 'b'));
//        System.out.println("\ndecimation second rule coef =  4: ");
//        cb=ImageAnalysis.decimationB(original,coef2,"Cb");
//        cr=ImageAnalysis.decimationB(original,coef2,"Cr");
//        ImageAnalysis.toFile(ImageAnalysis.recoverAfterDecimation(original,cb,cr,coef2),afterDecimationB4);
//        afterRGB =  ImageIO.read(new File(afterDecimationB4));
//        afterYCbCr=ImageAnalysis.RGBtoYCbCr(afterRGB,"none");
//        System.out.println("\nPSNR R: " + ImageAnalysis.PSNR(original, afterRGB, 'r'));
//        System.out.println("PSNR G: " + ImageAnalysis.PSNR(original, afterRGB, 'g'));
//        System.out.println("PSNR B: " + ImageAnalysis.PSNR(original, afterRGB, 'b'));
//        System.out.println("\nPSNR Y: " + ImageAnalysis.PSNR(y, afterYCbCr, 'r'));
//        System.out.println("PSNR Cb: " + ImageAnalysis.PSNR(y, afterYCbCr, 'g'));
//        System.out.println("PSNR Cr: " + ImageAnalysis.PSNR(y, afterYCbCr, 'b'));
//
//
//        RateAndSize rateAndSizeRGB = new RateAndSize("GreyImage/Rate/", image);
//        rateAndSizeRGB.rate("GreyImage/Rate/RGB","RGB");
//        RateAndSize rateAndSizeYCbCr = new RateAndSize("GreyImage/Rate/", image1);
//        rateAndSizeYCbCr.rate("GreyImage/Rate/YCbCr","YCbCr");
//        System.out.println("RGB"+"\nR: "+rateAndSizeRGB.sizeOfColor(rateAndSizeRGB.resultR)+"\nG: "
//                +rateAndSizeRGB.sizeOfColor(rateAndSizeRGB.resultG)+"\nB: "
//                +rateAndSizeRGB.sizeOfColor(rateAndSizeRGB.resultB));
//        System.out.println("YCbCr"+"\nY: "+rateAndSizeYCbCr.sizeOfColor(rateAndSizeYCbCr.resultR)+"\nCb: "
//                +rateAndSizeYCbCr.sizeOfColor(rateAndSizeYCbCr.resultG)+"\nCr: "
//                +rateAndSizeYCbCr.sizeOfColor(rateAndSizeYCbCr.resultB));

////        //Differential Pulse-Code Modulation
//        RateDifference rateDifferenceRGB = new RateDifference(image,"Differential Pulse-Code Modulation/");
//        rateDifferenceRGB.RateDifference_first_rules("Differential Pulse-Code Modulation/RGB");
//        System.out.println("RGB1"+"\nR: "+rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultR)+"\nG: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultG)+"\nB: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultB));
//        rateDifferenceRGB.RateDifference_second_rules("Differential Pulse-Code Modulation/RGB");
//        System.out.println("RGB2"+"\nR: "+rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultR)+"\nG: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultG)+"\nB: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultB));
//        rateDifferenceRGB.RateDifference_third_rules("Differential Pulse-Code Modulation/RGB");
//        System.out.println("RGB3"+"\nR: "+rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultR)+"\nG: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultG)+"\nB: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultB));
//        rateDifferenceRGB.RateDifference_fourth_rules("Differential Pulse-Code Modulation/RGB");
//        System.out.println("RGB4"+"\nR: "+rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultR)+"\nG: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultG)+"\nB: "
//                +rateDifferenceRGB.sizeOfColor(rateDifferenceRGB.resultB));
////
////
//        RateDifference rateDifferenceYCbCr = new RateDifference(image1,"Differential Pulse-Code Modulation/");
//        rateDifferenceYCbCr.RateDifference_first_rules("Differential Pulse-Code Modulation/YCbCr");
//        System.out.println("YCbCr1"+"\nY: "+rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultR)+"\nCb: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultG)+"\nCr: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultB));
//        rateDifferenceYCbCr.RateDifference_second_rules("Differential Pulse-Code Modulation/YCbCr");
//        System.out.println("YCbCr2"+"\nY: "+rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultR)+"\nCb: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultG)+"\nCr: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultB));
//        rateDifferenceYCbCr.RateDifference_third_rules("Differential Pulse-Code Modulation/YCbCr");
//        System.out.println("YCbCr3"+"\nY: "+rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultR)+"\nCb: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultG)+"\nCr: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultB));
//        rateDifferenceYCbCr.RateDifference_fourth_rules("Differential Pulse-Code Modulation/YCbCr");
//        System.out.println("YCbCr4"+"\nY: "+rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultR)+"\nCb: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultG)+"\nCr: "
//                +rateDifferenceYCbCr.sizeOfColor(rateDifferenceYCbCr.resultB));
//
//
//        // 1.2
////        BufferedImage bf = ImageIO.read(new File("kodim17.png"));
////        System.out.println(bf.getColorModel());
////        int i;
////        int j = 707*3;
////        System.out.println(j);
////        while(j%4!=0){
////            j++;
////        }
////        System.out.println(j);
////        i = j*773;
////        System.out.println(i);
//
//        String pathToSub = "Subgframe/";
//        Subframe subframe = new Subframe(image1,pathToSub);
//        subframe.doSubframe("Y00.bmp",0,0);
//        subframe.doSubframe("Y01.bmp",0,1);
//        subframe.doSubframe("Y10.bmp",1,0);
//        subframe.doSubframe("Y11.bmp",1,1);
//        File file00 = new File("Subgframe/Y00.bmp");
//        BufferedImage image00 = ImageIO.read(file00);
//        File file01 = new File("Subgframe/Y01.bmp");
//        BufferedImage image01 = ImageIO.read(file01);
//        File file10 = new File("Subgframe/Y10.bmp");
//        BufferedImage image10 = ImageIO.read(file10);
//        File file11 = new File("Subgframe/Y11.bmp");
//        BufferedImage image11 = ImageIO.read(file11);
//        subframe.modification01(image01,image00);
//        subframe.modification10(image01,image00,image10);
//        subframe.modification11(image01,image00,image10,image11);
//
//        File file00mod = new File("Subgframe/Y00.bmp");
//        BufferedImage image00mod = ImageIO.read(file00mod);
//        File file01mod = new File("Subgframe/mod01.bmp");
//        BufferedImage image01mod = ImageIO.read(file01mod);
//        File file10mod = new File("Subgframe/mod10.bmp");
//        BufferedImage image10mod = ImageIO.read(file10mod);
//        File file11mod = new File("Subgframe/mod11.bmp");
//        BufferedImage image11mod = ImageIO.read(file11mod);
//
//        RateAndSize rateAndSize00 = new RateAndSize("Subgframe/freq/", image00mod);
//        rateAndSize00.rate("Subgframe/freq/00","00");
//        System.out.println("00"+"\nY: "+rateAndSize00.sizeOfColor(rateAndSize00.resultR));
//        RateAndSize rateAndSize01 = new RateAndSize("Subgframe/freq/", image01mod);
//        rateAndSize01.rate("Subgframe/freq/01","01");
//        System.out.println("01"+"\nY: "+rateAndSize01.sizeOfColor(rateAndSize01.resultR));
//        RateAndSize rateAndSize10 = new RateAndSize("Subgframe/freq/", image10mod);
//        rateAndSize10.rate("Subgframe/freq/10","10");
//        System.out.println("10"+"\nY: "+rateAndSize10.sizeOfColor(rateAndSize10.resultR));
//        RateAndSize rateAndSize11 = new RateAndSize("Subgframe/freq/", image11mod);
//        rateAndSize11.rate("Subgframe/freq/11","11");
//        System.out.println("11"+"\nY: "+rateAndSize11.sizeOfColor(rateAndSize11.resultR));

    }
}