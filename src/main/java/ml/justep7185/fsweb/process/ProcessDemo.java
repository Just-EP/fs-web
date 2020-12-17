package ml.justep7185.fsweb.process;

import com.google.common.collect.Lists;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.jfree.svg.SVGGraphics2D;
import org.jfree.svg.ViewBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author yangjiawei
 * @date 2020-12-14 15:34
 **/
public class ProcessDemo {
    public static void main(String[] args) {
        ProcessDemo processDemo = new ProcessDemo();
//        processDemo.processDemo();
//        processDemo.testDecoder();
        String path = "D:\\微信图片_20201216115059.jpg";
        processDemo.qrEncodeSVG(path);
//        String text = processDemo.testDecoder(path);
//        processDemo.testEncoder(text);
    }
    public InputStream processDemo(){
        InputStream in = null;
        try {
            List<String> commands = Lists.newArrayList("cmd", "/c", "you-get -i https://b23.tv/kqMT6F");
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process exec = processBuilder.start();
            in = exec.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            while((line=br.readLine())!=null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
    public void qrEncode(String text) {
        //二维码内容

        //二维码图片宽度
        int width = 200;
        //高度
        int height = 200;
        //图片格式
        String format = "png";

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
        try {
            //编码
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e1) {
            e1.printStackTrace();
        }
        File outputFile = new File("src/1.png");
        try {
//            ImageIO.write();
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            //输出二维码图片
            if (bitMatrix != null) {
                MatrixToImageWriter.writeToStream(bitMatrix, format, fileOutputStream);
                MatrixToImageWriter.toBufferedImage(bitMatrix);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void qrEncodeSVG(String text) {
        //二维码内容

        //二维码图片宽度
        int width = 200;
        //高度
        int height = 200;
        //图片格式
        String format = "png";

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
        try {
            //编码
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            SVGGraphics2D g2 = new SVGGraphics2D(width, height);
            g2.drawImage(qrCodeImage, 0,0, width, height, null);
            String svgElement = g2.getSVGElement(null, true, null, null, null);
            File file = new File("D:/a.svg");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(svgElement);
            bufferedWriter.close();
            System.out.println();
        } catch (WriterException | IOException e1) {
            e1.printStackTrace();
        }

    }
}
