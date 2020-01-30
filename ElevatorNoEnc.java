package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Config;

public class ElevatorNoEnc {
    HardwareMap hwMap = null;

    //Elevator Parameters
    DcMotor elevator = null;
    int LIFT_COUNTS_PER_UPDOWN_EFFORT = 50;
    double LIFT_POWER = 1.0;

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;

        LIFT_POWER = config.getDouble("lift_power", 1.0);


        elevator = hwMap.get(DcMotor.class, "elevator");

        elevator.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void moveUp() {
        elevator.setPower(LIFT_POWER);
    }

    public void moveDown() {
        elevator.setPower(-LIFT_POWER);
    }

    public void stop() {
        elevator.setPower(0.0);
    }
}
