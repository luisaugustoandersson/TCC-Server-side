package br.edu.ucpel.tads.detection;

import com.googlecode.javacv.JavaCvErrorCallback;  
import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;



public class FaceDetection {

    // The cascade definition to be used for detection.
    private static final String CASCADE_FILE = "haarcascade_frontalface_alt.xml";

    public static void main(String[] args) throws Exception {
        
        new JavaCvErrorCallback().hashCode();

        // Load the original image.
        args = new String[1];
        args[0] = "data/01.jpg";
        
        IplImage originalImage = cvLoadImage(args[0], 1);

        // We need a grayscale image in order to do the recognition, so we
        // create a new image of the same size as the original one.
        IplImage grayImage = IplImage.create(originalImage.width(),
                originalImage.height(), IPL_DEPTH_8U, 1);

        // We convert the original image to grayscale.
        cvCvtColor(originalImage, grayImage, CV_BGR2GRAY);

        CvMemStorage storage = CvMemStorage.create();

        // We instantiate a classifier cascade to be used for detection, using the cascade definition.
        CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(
                cvLoad(CASCADE_FILE));

        // We detect the faces.
        CvSeq faces = cvHaarDetectObjects(grayImage, cascade, storage, 1.1, 1, 0);

        //We iterate over the discovered faces and draw yellow rectangles around them.
        for (int i = 0; i < faces.total(); i++) {
            CvRect r = new CvRect(cvGetSeqElem(faces, i));
            cvRectangle(originalImage, cvPoint(r.x(), r.y()),
                    cvPoint(r.x() + r.width(), r.y() + r.height()), CvScalar.YELLOW, 1, CV_AA, 0);
        }

        // Save the image to a new file.
        cvSaveImage(args[1], originalImage);
    }
}