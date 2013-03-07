package br.edu.ucpel.tads.tc.capture;

import br.edu.ucpel.tads.tc.enumeration.ImagePaths;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaptureImage {

    static IplImage image;

    private static void getImage() {
        final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        try {
            grabber.start();
            image = grabber.grab();
        } catch (Exception ex) {
            Logger.getLogger(CaptureImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void saveImage() {

        if (image != null) {
            System.out.println(image);
            cvSaveImage(ImagePaths.PATH_SAVE_IMAGE_CAPTURED + "/capturedImage.jpg", image);
        }

    }

    public void capture() {
        CaptureImage.getImage();
        CaptureImage.saveImage();
    }
}
