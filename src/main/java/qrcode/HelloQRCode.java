package qrcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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


        Result result1 = reader.decode(img);
        System.out.println("result1 : " + result1.getText());
        Result result2 = reader.decode(img);
        System.out.println("result2: " + result2.getText());
    }

    class Checkin {
        long checkinTimeMs;
        String msg;
    }

    public void doMoreComplicated() throws Exception {
        Checkin checkin = new Checkin();
        checkin.msg = "Aaron now we have json";
        checkin.checkinTimeMs = 101;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String json = gson.toJson(checkin);
        System.out.println("json is : " + json);
        ByteArrayOutputStream byteArrayOutputStream = QRCode.from(json).to(ImageType.JPG).stream();


        //   System.out.println("file is " + file.getCanonicalPath());
        OutputStream outputStream = new FileOutputStream ("AARON.jpg");
        byteArrayOutputStream.writeTo(outputStream);
    }
    public void readMoreComplicated() throws Exception {
        FileInputStream inputStream = new FileInputStream("AARON.jpg");

        BufferedImage image = ImageIO.read(new File("AARON.jpg"));

        QRCodeReader reader = new QRCodeReader();
        BinaryBitmap img=new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(image)));


        Result result1 = reader.decode(img);

        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        Checkin checkin = gson.fromJson(result1.getText(), Checkin.class);

        System.out.println("result1 : " + result1.getText());
        System.out.println("checkin: " + checkin.msg + " , " + checkin.checkinTimeMs);
    }
    public static void main(String[] args) throws  Exception {
        HelloQRCode c = new HelloQRCode();
        c.doMoreComplicated();

        c.readMoreComplicated();

    }
}
