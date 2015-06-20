package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
//import edu.wpi.first.wpilibj.CANJaguar.SpeedReference;
import edu.wpi.first.wpilibj.CANJaguar;
//import tests.TestApp;
//import edu.wpi.first.wpilibj.Ultrasonic.Unit;

public class EncoderClass extends IterativeRobot {

    Encoder enc;
    Timer time;
    Gyro gy;
    Watchdog wDog;
    Ultrasonic batEars;
    Ultrasonic.Unit kInches;
    Accelerometer Accel;
    CANTimeoutException can;
    CANJaguar wheel;
    double sensorSpeed = 0;
    double xFieldPos = 0.0;
    double yFieldPos = 0.0;
    

    public void robotInit() {
        time = new Timer();
        gy = new Gyro(1);
        // type= new Ultrasonic.Unit();
        // batEars = new Ultrasonic(1, 2, kInches);
        //wDog.getInstance();
        // Accel = new Accelerometer (1);
        //enc = new Encoder(1, 1);
        can = new CANTimeoutException();
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit() {
        super.teleopInit();
        wDog = Watchdog.getInstance();
        wDog.setExpiration(100000);
        //initGyroTest();
        System.out.println("teleopInit exiting; full control should = NOW!");
               //testGyro();
        initTestEncoder();
    }

    public void teleopContinuous() {
         try {
            wheel = new CANJaguar(2);
            wheel.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            wheel.configEncoderCodesPerRev(10000);
                        testEncoder();
        } catch (CANTimeoutException e) {
        }
        //testGyro();
    }

    public void teleopPeriodic() {
//System.out.println("Angle: " + gy.getAngle()+"       time: "+time.get());
    }

    private void initGyroTest() {
        gy.reset();
        time.reset();
        time.start();
    }

    private void testGyro() {
        //gy.free();
//        gy.reset();
        // gy.setSensitivity(7);
        //double ang;
//        time.start();
        //  if (time.get() <= 200000) {
        // ang = gy.getAngle();
        System.out.println("Angle: " + gy.getAngle() + "       time: " + time.get());
        // }
    }

    private void initTestEncoder() {
           }

    public void testEncoder() throws CANTimeoutException {

            //System.out.println("Position    :" + wheel.getPosition());
            System.out.println("Speed    :" + wheel.getSpeed());
    }

    public void testUltrasonic() {
        batEars.free();
        batEars.setEnabled(true);
        double dist = 0.0;
        time.start();
        while (time.get() <= 5000) {
            dist = batEars.getRangeInches();
            System.out.println("Inches away: " + dist);
        }
    }

    public void testAccelerometer() {
        Accel.free();
        double acceleration = 0.0;
        time.start();
        while (time.get() <= 5000) {
            acceleration = Accel.getAcceleration();
            System.out.println("Acceleration: " + acceleration);
        }
    }
    public void jagData() {
            }
        public void getCoords() {
        sensorSpeed += (Accel.getAcceleration());
        // with orientation being x=0 y=0 at corner of field, y being the longer length of the field
        yFieldPos += (sensorSpeed * Math.sin(gy.getAngle()));
        xFieldPos += (sensorSpeed * Math.cos(gy.getAngle()));
            }
    }
//        enc.free();
//        enc.reset();
//        enc.start();
        //double place = 0.0;
        //enc.setDistancePerPulse(.0005);
//        time.start();
        //System.out.println("--------------------------------\n------------------Rate---------------\n--------------------------------");
        //while (time.get() <= 5000) {
        //place = enc.getRate();
        //if (enc.getDirection()) {

//        } else {
//            System.out.println("backward   :" + enc.getRate());
//        }
        //}
        // enc.reset();
        //System.out.println("--------------------------------\n------------------Distance-------------\n--------------------------------");
        // while (time.get() <= 5000) {
        // place = enc.getDistance();
//            if (enc.getDirection()) {
//                System.out.println("forward    :" + enc.getRate());
//            } else {
//                System.out.println("backward   :" + enc.getRate());
//            }
        //}

//double a=wheel.getSpeed();

//        enc.reset();
//        enc.start();
        //time.start();