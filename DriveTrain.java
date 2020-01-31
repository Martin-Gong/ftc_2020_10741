package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.util.Range;

public class DriveTrain {
    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor leftBack  = null;
    DcMotor rightBack  = null;
    double speedx;
    double speedy;
    double offset;
    HardwareMap hwMap = null;

    static double FL_DRIVE_ADJUST = 1.0;
    static double FR_DRIVE_ADJUST = 1.0;
    static double RL_DRIVE_ADJUST = 1.0;
    static double RR_DRIVE_ADJUST = 1.0;

    public void init(HardwareMap Map, Config config) {
        hwMap = Map;

        FL_DRIVE_ADJUST = config.getDouble("fl_speed_adjust",1.0);
        FR_DRIVE_ADJUST = config.getDouble("fr_speed_adjust",1.0);
        RL_DRIVE_ADJUST = config.getDouble("rl_speed_adjust",1.0);
        RR_DRIVE_ADJUST = config.getDouble("rr_speed_adjust",1.0);

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

    public void move(double powerx, double powery, double turn){
        speedx = powerx;
        speedy = powery;
        offset = turn;
        leftFront.setPower(FL_DRIVE_ADJUST*Range.clip(speedy-speedx+offset,-1,1));
        rightFront.setPower(FR_DRIVE_ADJUST*Range.clip(speedy+speedx-offset,-1,1));
        leftBack.setPower(RL_DRIVE_ADJUST*Range.clip(speedy+speedx+offset,-1,1));
        rightBack.setPower(RR_DRIVE_ADJUST*Range.clip(speedy-speedx-offset,-1,1));
    }

    public void stop(){
        move(0,0,0);
    }
}