package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.hardware.Servo;

public class Elevator {
    HardwareMap hwMap = null;

    //Elevator Parameters
    DcMotor elevator = null;
    int LIFT_COUNTS_PER_UPDOWN_EFFORT = 50;
    double LIFT_POWER = 1.0;

    //Grabber Parameters
    Servo grabber = null;
    double GRABBER_CLOSED_POSITION = 0.0;
    double GRABBER_OPENED_POSITION = 0.8 ;

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;

        LIFT_POWER = config.getDouble("lift_power", 1.0);
        LIFT_COUNTS_PER_UPDOWN_EFFORT = config.getInt("lift_counts_per_updown_effort", 50);

        GRABBER_CLOSED_POSITION = config.getDouble("grabber_closed_position",0.0);
        GRABBER_OPENED_POSITION = config.getDouble("grabber_open_position",0.8);

        elevator = hwMap.get(DcMotor.class, "elevator");
        grabber = hwMap.get(Servo.class, "grabber");

        elevator.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        grabber.setDirection(Servo.Direction.FORWARD);
    }

    public void setLiftZeroPosition() {
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    
    public void moveUp() {
        int newTarget;
        newTarget  = elevator.getCurrentPosition() + LIFT_COUNTS_PER_UPDOWN_EFFORT;
        elevator.setPower(LIFT_POWER);
        elevator.setTargetPosition(newTarget);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveDown() {
        int newTarget;
        newTarget  = elevator.getCurrentPosition() - LIFT_COUNTS_PER_UPDOWN_EFFORT;
        elevator.setPower(LIFT_POWER);
        elevator.setTargetPosition(newTarget);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void stop() {
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        elevator.setPower(0.0);
    }

    public void closeGrabber() {
        grabber.setPosition(GRABBER_CLOSED_POSITION);
    }

    public void openGrabber() {
        grabber.setPosition(GRABBER_OPENED_POSITION);
    }
}