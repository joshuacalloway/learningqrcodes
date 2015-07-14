package qrcode;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.stream.Stream;

/**
 * Created by jc on 7/14/15.
 */
public class HelloQRCode {

    public void doHello() throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = QRCode.from("Hello Aaron").to(ImageType.JPG).stream();
     //   System.out.println("file is " + file.getCanonicalPath());
        OutputStream outputStream = new FileOutputStream ("AARON.jpg");
        byteArrayOutputStream.writeTo(outputStream);
    }
    public void readHello() throws Exception {
        FileInputStream inputStream = new FileInputStream("AARON.jpg");

        BufferedImage image = ImageIO.read(new File("AARON.jpg"));

        QRCodeReader reader = new QRCodeReader();
        BinaryBitmap img=new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(image)));


        Result result = reader.decode(img);
        System.out.println("result : " + result.getText());
    }
    public static void main(String[] args) throws  Exception {
        HelloQRCode c = new HelloQRCode();
        c.doHello();

        c.readHello();

    }
}
