package edu.wpi.first.wpilibj.examples;

import edu.team2974.util.DashboardLogger;
import edu.wpi.first.smartdashboard.properties.DoubleProperty;
import edu.wpi.first.smartdashboard.properties.IntegerProperty;
import edu.wpi.first.wpijavacv.*;
import edu.wpi.first.wpilibj.examples.tracking.Target;
import edu.wpi.first.wpilibj.examples.tracking.TrackingManager;
import edu.wpi.first.wpilibj.networking.NetworkTable;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Greg Granito
 */
public class SquareTrackerExtension extends WPICameraExtension
{
    /*
     * To however is reading this, I apologize in advance. I got to this pretty
     * late, and this is a lot of code. So I tried to make this as entertaining
     * as possible. which probably isn't going to be to who ever is reading
     * this. Don't judge. Like to see you write better comments. No really, next
     * time do my job for me.
     */

    public static final String NAME = "CamTrackerWidget";
    public static final int IMAGE_WIDTH = 640;
    public static final int IMAGE_HEIGHT = 480;
    /**
     * whats the aim threshold?
     */
    public static final int AIM_THRESHOLD = 10;
    /**
     * probably used in ratios
     */
    public static final double TARGET_WIDTH = 2;
    public static final double TARGET_HEIGHT = 1.5;
    /**
     * this looks like it needs to be changed This isn't used. Not practical for
     * us.
     */
    private static final int HORIZON_LINE_Y = IMAGE_HEIGHT - 30;
    /**
     * LOOK a network table. Seems useful, you know for storing stuff about the
     * camera
     */
    private NetworkTable table = NetworkTable.getTable("camera");
    /**
     * used for manipulating the shooter based on info picked up here.
     */
    private NetworkTable shooterRotate = NetworkTable.getTable("ShooterRotateCommand");
    private NetworkTable targetTable = NetworkTable.getTable("Target Table");
    /*
     * how likely the blob is a polygon?
     */
    private DoubleProperty polygonQuality;
    /*
     * Erode & dilate get rid of interference in the image
     */
    private IntegerProperty erodeAmount;
    private IntegerProperty dilateAmount;
    /**
     * max red in the image
     */
    private IntegerProperty redMaxProperty;
    /**
     * minimum blue in the image
     */
    private IntegerProperty blueMinProperty;
    private IntegerProperty blueMaxProperty;
    private IntegerProperty greenMinProperty;
    /**
     * the angle the camera is tilted
     */
    private DoubleProperty cameraViewAngle;
    /**
     * hey look I found a polygon!
     */
    private List<WPIPolygon> foundPolygons;
    private Point mouseLocation;
    /*
     * the target that is selected, could do this all day
     */
    private Target selectedTarget = null;
    /*
     * Manages tracking? yes? Good. Move along
     */
    private TrackingManager tracker;
    
    /*
     * creating logger for dashboard
     */
    private DashboardLogger dashLogger;

    public SquareTrackerExtension() {
        /*
         * Da da da dah! Numbers, that seem robot specific. Maybe, just maybe,
         * this is the stuff we need to fiddle with
         */
        polygonQuality = new DoubleProperty(this, "Polygon Quality", 45);
        erodeAmount = new IntegerProperty(this, "Erode Amount", 0);
        dilateAmount = new IntegerProperty(this, "Dilate Amount", 6);
        cameraViewAngle = new DoubleProperty(this, "View Angle", 54);
        redMaxProperty = new IntegerProperty(this, "Red Maximum", 157);
        blueMinProperty = new IntegerProperty(this, "Blue Minimum", 28);
        blueMaxProperty = new IntegerProperty(this, "Blue Maximum", 166);
        greenMinProperty = new IntegerProperty(this, "Green Minimum", 219);
        dashLogger=DashboardLogger.getInstance();
        dashLogger.logMessage("In the constructor");
        foundPolygons = new ArrayList<WPIPolygon>();
        tracker = new TrackingManager();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    public WPIImage processImage(WPIColorImage rawImage) {
        dashLogger.logMessage("Starting Process Image");
        /*
         * Getting the threshold values this way grabs the values off of the current
         * SmartDashboard settings.
         */
        int redMax = redMaxProperty.getValue();
        int blueMin = blueMinProperty.getValue();
        int blueMax = blueMaxProperty.getValue();
        int greenMin = greenMinProperty.getValue();

        /*
         * make a two color image by finding colors in the rgb threshold so
         * fiddle will be needed here. yes? Speaking of which I don't know how
         * to play. Singing is better. Why I joined chorus. Chris, everyone
         * knows that violas trump singing without even trying.
         */
        WPIBinaryImage greenChan = rawImage.getGreenChannel().getThreshold(
                greenMin);
        WPIBinaryImage redChan = rawImage.getRedChannel().getThresholdInverted(
                redMax);
        WPIBinaryImage blueChan1 = rawImage.getBlueChannel().getThreshold(
                blueMin);
        WPIBinaryImage blueChan2 = rawImage.getBlueChannel().
                getThresholdInverted(blueMax);
        
        /*
         * and looks needed but not sure what its doing here.
         */
        greenChan.and(redChan);
        greenChan.and(blueChan1);
        greenChan.and(blueChan2);
        dashLogger.logMessage("Binary Image Completed");

        greenChan.erode(erodeAmount.getValue());
        greenChan.dilate(dilateAmount.getValue());
        /*
         * find the lines!
         */
        WPIContour[] contours = greenChan.findContours();
        /*
         * you're either a square or you're bad. Sorry circles no tracking party
         * for you.
         */
        List<WPIPolygon> squares = new ArrayList<WPIPolygon>();
        List<WPIPolygon> badPolygons = new ArrayList<WPIPolygon>();

        /*
         * find initial polygons approximates a polygon based on the quality you
         * think is tolerable fill it in, and count the corners, if you have 4
         * you're in if you don't, sorry not on the list bub
         */
        dashLogger.logMessage("Finding squares");
        for (WPIContour c : contours) {
            WPIPolygon p = c.approxPolygon(polygonQuality.getValue());
            if (p.isConvex() && p.getNumVertices() == 4) {
                squares.add(p);
            } else {
                badPolygons.add(p);
            }
        }

        /*
         * filter polygons with the same center (i.e. remove inner)
         */ 
        List<WPIPolygon> polygonsFiltered = filterSimilar(squares);

        /*
         * draw the bad polys, cause the bouncer needs to know who to throw out
         */
        for (WPIPolygon p : badPolygons) {
            rawImage.drawPolygon(p, WPIColor.RED, 3);
        }

        /*
         * draw the good polys, cause they are on the list and are invited
         */
        
        WPIPolygon highestPoly = null;
        int maxHeight = 480;
        for (WPIPolygon p : polygonsFiltered) {
            rawImage.drawPolygon(p, WPIColor.CYAN, 1);
          
            /*
             * draw center points
             */
            int cx = p.getX() + (p.getWidth() / 2);
            int cy = p.getY() + (p.getHeight() / 2);
            if(cy<maxHeight)
            {
                maxHeight = cy;
                highestPoly = p;
            }
            rawImage.drawPoint(new WPIPoint(cx, cy), WPIColor.YELLOW, 2);
            
        }
        
        if(highestPoly!=null)
        {
            //selectedTarget = new Target(1, highestPoly);
           // dashLogger.logMessage("Width in pixels: "+ highestPoly.getWidth());
            //dashLogger.logMessage("Rectangle Height in pixels: "+ highestPoly.getHeight());
            //dashLogger.logMessage("Center y of Rectangle: "+maxHeight);
           // dashLogger.logMessage("Calculated Distance: "+ getDistance(highestPoly));
            
             try{
            if(selectedTarget==null)
            {
                selectedTarget = new Target(1337,highestPoly); 
            }
            selectedTarget.setPolygon(highestPoly);
            dashLogger.logMessage("Width in pixels: "+ selectedTarget.getPolygon().getWidth());
            dashLogger.logMessage("Rectangle Height in pixels: "+ selectedTarget.getPolygon().getHeight());
            dashLogger.logMessage("Center y of Rectangle: "+maxHeight);
            dashLogger.logMessage("Calculated Distance: "+ getDistance(selectedTarget.getPolygon()));
            }catch(NullPointerException ex){
                dashLogger.logMessage(ex.getMessage());
            }

        }
        else
        {
            dashLogger.logMessage("There is no polygon");
        }
        foundPolygons.clear();
        foundPolygons.addAll(polygonsFiltered);

        /*
         * process target changes
         */
        tracker.processFrame(foundPolygons);

        /*
         * update the shooter if needed this is where auto logic goes, if we
         * want it here probably want to put the network table stuff here
         * instead cause we want to give orders crio side
         */
        if (selectedTarget != null) {
            if (!tracker.getKnownTargets().contains(selectedTarget)) {
                selectedTarget = null;
                stopRotation();
                targetTable.putBoolean("Target Found", false);
            } else {
                targetTable.putBoolean("Target Found", true);
                WPIPolygon target = selectedTarget.getPolygon();
                int cxTarget = target.getX() + (target.getWidth() / 2); // 500
                int cxImage = IMAGE_WIDTH / 2; // 320

                int dx = cxTarget - cxImage;

                int cy = target.getY() + (target.getHeight() / 2);
                targetTable.putInt("Center Y", cy);

                if (Math.abs(dx) < AIM_THRESHOLD) {
                    /*
                     * ready to shoot, stop rotating
                     */
                    stopRotation();

                    /*
                     * do other things TODO: shoot here
                     */
                } else {
                    /*
                     * turn towards target
                     */
                    rotate(dx);
                }
            }
        }

        /*
         * return greenChan;
         */
        return rawImage;
    }

    private List<WPIPolygon> filterSimilar(List<WPIPolygon> polys) {
        List<WPIPolygon> ret = new ArrayList<WPIPolygon>();
        ret.addAll(polys);

        List<WPIPolygon> removalQueue = new ArrayList<WPIPolygon>();

        for (WPIPolygon p : polys) {
            /*
             * find all polygons with similar center points
             */
            List<WPIPolygon> similar = getSimilar(p, polys);

            /*
             * find the largest of the similar polygons
             */
            WPIPolygon largest = getLargest(similar);

            /*
             * remove the largest poly
             */
            similar.remove(largest);

            /*
             * queue the smaller polygons for removal
             */
            removalQueue.addAll(similar);
        }

        /*
         * remove everything in the removal queue
         */
        for (WPIPolygon p : removalQueue) {
            removeAll(p, ret);
        }

        return ret;
    }

    /**
     * Finds polygons with similar centers
     *
     * @param poly the polygon to use for comparison
     * @param pool the pool of polygons to compare against
     * @return a list of similar polygons
     */
    private List<WPIPolygon> getSimilar(WPIPolygon poly, List<WPIPolygon> pool) {
        List<WPIPolygon> ret = new ArrayList<WPIPolygon>();

        int xCenter = poly.getX() + (poly.getWidth() / 2);
        int yCenter = poly.getY() + (poly.getHeight() / 2);

        for (WPIPolygon p : pool) {
            int pcx = p.getX() + (p.getWidth() / 2);
            int pcy = p.getY() + (p.getHeight() / 2);

            int dx = Math.abs(pcx - xCenter);
            int dy = Math.abs(pcy - yCenter);

            int distSquared = (dx * dx) + (dy * dy);

            if (distSquared < 20 * 20) {
                ret.add(p);
            }
            /*
             * ignore this rect if it's too small
             */
        }

        return ret;
    }

    public WPIPolygon getLargest(List<WPIPolygon> pool) {
        WPIPolygon ret = null;

        for (WPIPolygon p : pool) {
            if (ret == null || p.getArea() > ret.getArea()) {
                ret = p;
            }
        }

        return ret;
    }

    public <T> void removeAll(T obj, List<T> pool) {
        while (pool.contains(obj)) {
            pool.remove(obj);
        }
    }

    /*
     * We don't use this method.
     */
    public double getDistanceHorizontal(WPIPolygon p) {
        return Math.sqrt(Math.pow(getDistanceCorners(p), 2)
                - Math.pow(getDistanceVertical(p), 2));
    }

    /*
     * We don't use this one either.
     */
    public double getDistanceVertical(WPIPolygon p) {
        int heightAbove = p.getY() + p.getHeight() / 2 - HORIZON_LINE_Y;
        double trueHeight = heightAbove * TARGET_HEIGHT / p.getHeight();
        return trueHeight;
    }

    public double getDistanceCorners(WPIPolygon p) {
        double imageWidth = IMAGE_WIDTH;
        /*
         * double polyWidth = p.getWidth() - dilateAmount.getValue() * 2;
         */
        List<WPIPoint> points = Arrays.asList(p.getPoints());

        List<WPIPoint> right = new ArrayList<WPIPoint>();
        right.addAll(points);
        right.remove(findMinimum(right));
        right.remove(findMinimum(right));

        List<WPIPoint> left = new ArrayList<WPIPoint>();
        left.addAll(points);
        left.removeAll(right);

        double leftX = averageX(left);
        double leftY = averageY(left);
        double rightX = averageX(right);
        double rightY = averageY(right);

        double dx = Math.abs(leftX - rightX);
        double dy = Math.abs(leftY - rightY);

        double width = Math.sqrt((dx * dx) + (dy * dy)) - (dilateAmount.getValue() * 2);

        double fovWidth = ((Math.sqrt(6.25) * imageWidth) / width) / 2;
        double theta = cameraViewAngle.getValue() / 2;

        double distance = fovWidth / Math.tan(Math.toRadians(theta));
        return distance;
    }

    public double getDistance(WPIPolygon p) {
        double pixelHeight = p.getHeight() - dilateAmount.getValue() * 3 / 2;
        double fovHeight = (TARGET_HEIGHT * IMAGE_HEIGHT) / pixelHeight;

        double angle = cameraViewAngle.getValue() / 2;
        double distance = ((fovHeight / 2) / (Math.tan(Math.toRadians(angle))));

        System.out.println("PixelHeight = " + pixelHeight);
        System.out.println("foveHeight = " + fovHeight);
        System.out.println("Distance = " + distance);
        System.out.println();

        return distance;
    }

    private WPIPoint findMinimum(List<WPIPoint> points) {
        WPIPoint ret = null;

        for (WPIPoint p : points) {
            if (ret == null || p.getX() < ret.getX()) {
                ret = p;
            }
        }

        return ret;
    }

    public double averageX(List<WPIPoint> points) {
        double sum = 0;

        for (WPIPoint p : points) {
            sum += p.getX();
        }

        return sum / points.size();
    }

    public double averageY(List<WPIPoint> points) {
        double sum = 0;

        for (WPIPoint p : points) {
            sum += p.getY();
        }

        return sum / points.size();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getDrawnImage() != null) {
            BufferedImage image = getDrawnImage();
            Graphics2D g2d = image.createGraphics();
            int horizY = image.getHeight() - 30;

            for (WPIPolygon p : foundPolygons) {
                int cx = p.getX() + (p.getWidth() / 2);
                int cy = p.getY() + (p.getHeight() / 2);
                int heightAboveSeen = -p.getY() + horizY;
                double heightAboveTrue =
                        ((double) heightAboveSeen) * TARGET_HEIGHT / ((double) p.
                        getHeight());
                /*
                 * Now do point calculations to find the top and bottom corners
                 */
                WPIPoint[] points = p.getPoints();
                double upperWidth;
                double lowerWidth;
                double distance;
                WPIPoint p1 = null;
                WPIPoint p2 = null;
                for (int i = 0; i < 4; i++) {
                    if (p1 == null || p1.getY() < points[i].getY()) {
                        if (p2 == null || p1.getY() > p2.getY()) {
                            p2 = p1;
                        }
                        p1 = points[i];
                    } else if (p2 == null || p2.getY() < points[i].getY()) {
                        p2 = points[i];
                    }
                }

                upperWidth = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
                        + Math.pow(p1.getY() - p2.getY(), 2));

                System.out.println("UpperWidth = " + upperWidth);

                p1 = (p2 = null);

                for (int i = 0; i < 4; i++) {
                    if (p1 == null || p1.getY() > points[i].getY()) {
                        if (p2 == null || p2.getY() > p1.getY()) {
                            p2 = p1;
                        }
                        p1 = points[i];
                    } else if (p2 == null || p2.getY() > points[i].getY()) {
                        p2 = points[i];
                    }
                }

                lowerWidth = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
                        + Math.pow(p1.getY() - p2.getY(), 2));

                System.out.println("LowerWidth = " + lowerWidth);
                System.out.println("HeightActual = " + heightAboveTrue);
                System.out.println();

                distance = heightAboveSeen / ((upperWidth / lowerWidth) - 1) / (heightAboveTrue + TARGET_HEIGHT);
                distance = Math.sqrt(distance);

                g2d.setColor(Color.WHITE);
                g2d.drawString(String.format("D: %.3f ft", getDistance(p)),
                        cx + 5, cy);
                g2d.drawString(String.format("H: %d", p.getHeight()),
                        cx + 5, cy - 15);

                if (mouseLocation != null) {
                    Polygon awtPolygon = convert(p);
                    if (awtPolygon.contains(mouseLocation)) {
                        g2d.setColor(Color.GREEN);
                        GraphicsUtil.alpha(g2d, 0.50f);
                        g2d.fill(awtPolygon);
                        GraphicsUtil.alpha(g2d, 1);

                        g2d.setColor(Color.YELLOW);
                        g2d.draw(awtPolygon);

                        g2d.setColor(Color.BLACK);
                    }
                }
            }

            g2d.setColor(Color.WHITE);

            g2d.drawString("Targets Known: " + tracker.getKnownTargets().size(),
                    15, 15);
            if (shooterRotate.containsKey("running")) {
                boolean running = shooterRotate.getBoolean("running");
                g2d.drawString("Aiming:        " + running, 15, 30);

                if (shooterRotate.containsKey("direction") && running) {
                    g2d.drawString("Aim Direction: " + shooterRotate.getInt(
                            "direction"), 15, 45);
                }
            }

            for (Target t : tracker.getKnownTargets()) {
                WPIPolygon poly = t.getPolygon();

                int cx = poly.getX() + (poly.getWidth() / 2);
                int cy = poly.getY() + (poly.getHeight() / 2);

                g2d.drawString("T: " + t.getNumber(), cx + 5, cy + 12);
            }

            if (selectedTarget != null) {
                Polygon p = convert(selectedTarget.getPolygon());

                GraphicsUtil.alpha(g2d, 0.5f);
                g2d.setColor(Color.RED);
                g2d.fill(p);
                GraphicsUtil.alpha(g2d, 1);

                g2d.setColor(Color.ORANGE);
                g2d.draw(p);
                double x = getDistanceHorizontal(selectedTarget.getPolygon());
                double y = getDistanceVertical(selectedTarget.getPolygon());
                targetTable.putDouble("Distance", getDistance(selectedTarget.
                        getPolygon()));

                /*
                 * theata and velocity seem to be the other team's version of a
                 * power calculator, or what they feed into their power
                 * conversion However I have no idea why they picked the numbers
                 * they did so these values may be unusable for us
                 */
//				double theta = 52.36 - 0.5991*x + 36.73 * y 
//						+ 0.005431 * Math.pow(x, 2) - 0.5144 * x * y 
//						- 13.63 * Math.pow(y, 2) + 0.001624 * Math.pow(x, 2) * y
//						+ 0.2275 * x * Math.pow(y, 2) + 2.35 * Math.pow(y, 3)
//						- 0.0008125 * Math.pow(x*y, 2) 
//						- 0.03133 * x * Math.pow(y, 3) - 0.1885 * Math.pow(y, 4)
//						 + 0.0000695 * Math.pow(x*y, 2) * y 
//						+ 0.001408 * x * Math.pow(y, 4) 
//						+ 0.005756 * Math.pow(y, 5);
//				
//				double velocity = 13.77 + 0.6473 * x + 12.26 * y 
//						+ 0.001124 * Math.pow(x, 2) - 0.1462 * x * y 
//						+ 0.02471 * Math.pow(y, 2) 
//						+ 0.0005207 * Math.pow(x, 2) * y
//						+ 0.008972 * x * Math.pow(y, 2)
//						+ 0.003813 * Math.pow(y, 3);
//				
//				shooterAim.putDouble("velocity", velocity);
//				shooterAim.putDouble("theta", theta);
            }

            g2d.drawLine(0, horizY, image.getWidth(), horizY);

            g2d.dispose();
        }

        super.paintComponent(g);
    }

    public Polygon convert(WPIPolygon poly) {
        Polygon ret = new Polygon();

        for (WPIPoint p : poly.getPoints()) {
            ret.addPoint(p.getX(), p.getY());
        }

        return ret;
    }
    ;
	private MouseAdapter mouseListener = new MouseAdapter()
    {

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseLocation = e.getPoint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseLocation = null;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (Target t : tracker.getKnownTargets()) {
                Polygon p = convert(t.getPolygon());

                if (p.contains(e.getPoint())) {
                    selectedTarget = t;

//                    JOptionPane.showMessageDialog(
//                            SquareTrackerExtension.this, "Polygon selected!");
                }
            }
        }
    };

    private void rotate(int dx) {
        if (dx > 0) {
            if (dx > 50) {
                shooterRotate.putDouble("direction", .35);
            } else if(dx > 35){
                
                shooterRotate.putDouble("direction", dx/200);
            }
            else
            {
                shooterRotate.putDouble("direction", .175);
            }
        } else if (dx < 0) {
            if (dx < -50) {
                shooterRotate.putDouble("direction", -.35);
            } else if(dx < -35){
                
                shooterRotate.putDouble("direction", dx/200);
            }
            else
            {
                shooterRotate.putDouble("direction", -.175);
            }
        } else {
            shooterRotate.putDouble("direction", 0);
        }

        shooterRotate.putBoolean("running", true);
    }

    private void stopRotation() {
        shooterRotate.putBoolean("running", false);
        shooterRotate.putDouble("direction", 0);
    }
}