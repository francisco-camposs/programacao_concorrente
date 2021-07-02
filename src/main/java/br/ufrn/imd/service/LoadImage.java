package br.ufrn.imd.service;

import br.ufrn.imd.model.ImageTagged;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
public class LoadImage {

    private String path;

    private String tag;

    private static final Size SIZE = new Size(512, 512);

    public ImageTagged searchImage() {
       Mat img = loadImage(path);
       Byte[] buffer = matToArray(img);
       return new ImageTagged(tag, buffer);
    }

    private Mat loadImage(String path) {
        Mat img = Imgcodecs.imread(path);
        Mat resizeImage = new Mat();
        Imgproc.resize(img, resizeImage, SIZE);
        return resizeImage;
    }

    private Byte[] matToArray(Mat img) {
        byte[] buffer = new byte[(int) (img.total() * img.channels())];
        img.get(0, 0, buffer);
        Byte[] bytes = new Byte[buffer.length];
        Arrays.setAll(bytes, n -> buffer[n]);
        return bytes;
    }

}
