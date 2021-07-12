package br.ufrn.imd.service;

import br.ufrn.imd.model.ImageTagged;
import lombok.Getter;
import lombok.Setter;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.concurrent.Semaphore;

@Getter
@Setter
public class LoadImageService {

    private final static Semaphore semaphore = new Semaphore(1);

    private final Size SIZE = new Size(512, 512);

    public ImageTagged searchImage(String path, String tag) {
       Mat img = loadImage(path);
       byte[] buffer = matToArray(img);
       return new ImageTagged(tag, buffer);
    }

    private Mat loadImage(String path) {
        Mat img = Imgcodecs.imread(path);
        Mat resizeImage = new Mat();
        Imgproc.resize(img, resizeImage, SIZE);
        return resizeImage;
    }

    private byte[] matToArray(Mat img) {
        byte[] buffer = new byte[(int) (img.total() * img.channels())];
        try{
            semaphore.acquire(1);
            img.get(0, 0, buffer);
            semaphore.release(1);
        } catch (InterruptedException e) {
            System.out.println("Occurred an error while transform image");
            e.printStackTrace();
        }

        return buffer;
    }

}
