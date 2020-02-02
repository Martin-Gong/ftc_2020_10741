package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.Config;
import org.firstinspires.ftc.teamcode.util.telemetry.TelemetryWrapper;
import javax.microedition.khronos.opengles.GL;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Dictionary;
import java.util.Map;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.util.Range;



@Autonomous(name = "test")
public class test extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private Config config = new Config(Config.configFile);
    DcMotor motor0, motor1, motor2, motor3;

    @Override
    public void runOpMode() {
        DriveTrainByEncoder driveTrain = new DriveTrainByEncoder();
        driveTrain.init(hardwareMap,config);
        double DISTANCE_TO_LINE = 100;
        DISTANCE_TO_LINE = config.getDouble("distance_to_line_from_build",100);

        //waiting for user to press start
        waitForStart();

        TelemetryWrapper.init(telemetry,7);

        runtime.reset();
        TelemetryWrapper.setLine(2,"" + opModeIsActive() + runtime.seconds());

        motor0 = hardwareMap.get(DcMotor.class, "fl_drive");
        motor1= hardwareMap.get(DcMotor.class, "rl_drive");
        motor2 = hardwareMap.get(DcMotor.class, "fr_drive");
        motor3 = hardwareMap.get(DcMotor.class, "rr_drive");

        driveTrain.encoderDrive(0.8,0.0,DISTANCE_TO_LINE,0.0,10);
    }

    public static void sleep(int time){
        try{
            Thread.sleep(time);
        } catch (Exception e) {}
    }
}