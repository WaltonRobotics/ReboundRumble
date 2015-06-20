package cameraWidgetPackage;

import edu.wpi.first.smartdashboard.camera.WPICameraExtension;
import edu.wpi.first.wpijavacv.*;
import edu.wpi.first.wpilibj.networking.NetworkTable;
import java.util.ArrayList;

/**
 *
 * @author Walton Robotics
 */
public class CamWidget extends WPICameraExtension {

    public static final String NAME = "Laptop Camera Square Tracker";

    private static NetworkTable getTable(){
        return cameraTable;
    }

    private static void putBoolean(String string, boolean b) {
        throw new UnsupportedOperationException("found");
    }

    private void putDouble(String string, double x) {
        throw new UnsupportedOperationException();
    }
    WPIColor targetColor = new WPIColor(0, 255, 0);
    static NetworkTable cameraTable = NetworkTable.getTable("camera");
    NetworkTable table = CamWidget.getTable();

    public WPIImage processImage(WPIColorImage rawImage) {

        WPIBinaryImage blueBin = rawImage.getBlueChannel().getThresholdInverted(60);
        WPIBinaryImage greenBin = rawImage.getGreenChannel().getThresholdInverted(60);
        WPIBinaryImage redBin = rawImage.getRedChannel().getThresholdInverted(60);
        WPIBinaryImage finalBin = blueBin.getAnd(redBin).getAnd(greenBin);

//h=17 13/16
//w=24 1/16
//ratio=0.74
//between .365 and 1.115

        finalBin.erode(2);
        finalBin.dilate(6);

        WPIContour[] contours = finalBin.findContours();
        ArrayList<WPIPolygon> polygons = new ArrayList<WPIPolygon>();

        for (WPIContour c : contours) {
            double ratio = ((double) c.getHeight()) / ((double) c.getWidth());
            if (ratio < .365 && ratio > 1.115) {
                polygons.add(c.approxPolygon(45));
            }
        }
        ArrayList<WPIPolygon> possiblePolygons = new ArrayList<WPIPolygon>();

        for (WPIPolygon p : polygons) {
            if (p.isConvex() && p.getNumVertices() == 4) {
                possiblePolygons.add(p);
            } else {
                rawImage.drawPolygon(p, WPIColor.CYAN, 5);
            }
        }
        WPIPolygon square = null;
        int squareArea = 0;

        for (WPIPolygon p : possiblePolygons) {
            rawImage.drawPolygon(p, WPIColor.GREEN, 5);
            for (WPIPolygon q : possiblePolygons) {
                if (p == q) {
                    continue;
                }

                int pCenterX = (p.getX() + (p.getWidth() / 2));

                int qCenterX = q.getX() + (q.getWidth() / 2);

                int pCenterY = (p.getY() + (p.getHeight() / 2));

                int qCenterY = q.getY() + (q.getHeight() / 2);

                rawImage.drawPoint(new WPIPoint(pCenterX, pCenterY), targetColor, 5);
                rawImage.drawPoint(new WPIPoint(qCenterX, qCenterY), targetColor, 5);

                if (Math.abs(pCenterX - qCenterX) < 20
                        && Math.abs(pCenterY - qCenterY) < 20) {
                    int pArea = Math.abs(p.getArea());
                    int qArea = Math.abs(q.getArea());
                    if (pArea > qArea) {
                        square = p;
                        squareArea = pArea;
                    } else {
                        square = q;
                        squareArea = qArea;
                    }
                    break;
                }
            }
        }

        if (square != null) {
            double x = square.getX() + (square.getWidth() / 2);
            x = (2 * (x / rawImage.getWidth())) - 1;

            double area = ((double) squareArea) / ((double) (rawImage.getWidth() * rawImage.getHeight()));

            synchronized (cameraTable) {
                cameraTable.beginTransaction();
                cameraTable.putBoolean("found", true);
                cameraTable.putDouble("x", x);
                cameraTable.putDouble("area", area);
                cameraTable.endTransaction();
            }
            CamWidget.putBoolean("found", true);
            table.putDouble("X", x);
            table.putDouble("Area", area);
            rawImage.drawPolygon(square, targetColor, 7);
        } else {
            table.putBoolean("found", false);
        }

        return rawImage;
    }
}