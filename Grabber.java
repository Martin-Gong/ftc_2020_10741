import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Config;

public class Grabber {
    HardwareMap hwMap = null;

    //Grabber Parameters
    Servo grabber = null;
    double GRABBER_CLOSED_POSITION = 0.0;
    double GRABBER_OPENED_POSITION = 0.8 ;

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;

        GRABBER_CLOSED_POSITION = config.getDouble("grabber_closed_position",0.0);
        GRABBER_OPENED_POSITION = config.getDouble("grabber_open_position",0.8);

        grabber = hwMap.get(Servo.class, "grabber");

        grabber.setDirection(Servo.Direction.FORWARD);
    }

    public void closeGrabber() {
        grabber.setPosition(GRABBER_CLOSED_POSITION);
    }

    public void openGrabber() {
        grabber.setPosition(GRABBER_OPENED_POSITION);
    }
}
