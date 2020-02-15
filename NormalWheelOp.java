package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.ButtonHelper;
import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.util.Range;

public class NormalWheelOp extends LinearOpMode {
    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor leftBack  = null;
    DcMotor rightBack  = null;
    HardwareMap hwMap = null;
    private ButtonHelper helper;

    @Override
    public void runOpMode () {
        helper = new ButtonHelper(gamepad1);

        waitForStart();
        while(opModeIsActive()) {
            if(gamepad1.left_stick_y != 0.0) {
                leftFront.setPower(gamepad1.left_stick_y);
                rightFront.setPower(gamepad1.left_stick_y);
                leftBack.setPower(gamepad1.left_stick_y);
                rightBack.setPower(gamepad1.left_stick_y);
            }
            else if(gamepad1.right_stick_x != 0.0) {
                leftFront.setPower(gamepad1.right_stick_x);
                rightFront.setPower(-gamepad1.right_stick_x);
                leftBack.setPower(gamepad1.right_stick_x);
                rightBack.setPower(-gamepad1.right_stick_x);
            }
            else {
                leftFront.setPower(0);
                rightFront.setPower(0);
                leftBack.setPower(0);
                rightBack.setPower(0);
            }
        }
    }

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;

        leftFront = hwMap.get(DcMotor.class, "fl_drive");
        rightFront = hwMap.get(DcMotor.class, "fr_drive");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setPower(0);
        rightFront.setPower(0);

        leftBack = hwMap.get(DcMotor.class, "rl_drive");
        rightBack = hwMap.get(DcMotor.class, "rr_drive");
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}