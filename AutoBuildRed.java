package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Config;


@Autonomous(name = "AutoBuildRed")
@Disabled
public class AutoBuildRed extends LinearOpMode{

    private DriveTrainByEncoder driveTrain = new DriveTrainByEncoder();
    private ElapsedTime runTime = new ElapsedTime();
    private Config config = new Config(Config.configFile);

//    private

    private static final float mmPerInch     = 25.4f;
    private static final float robotX        = 17 * mmPerInch;
    private static final float robotY        = 14.5f * mmPerInch;
    private static final float halfCourt = 23.625f * mmPerInch;
    private double DISTANCE_TO_FOUNDATION_X = 1200.2f - robotY;
    private double DISTANCE_TO_FOUNDATION_Y = halfCourt - 539.75f - 600.08f;
    private double TURNING_ANGLE = 0;
    private double TURNING_RIGHT_ANGLE = 90;
    private double SPEED = 1.0;


    @Override
    public void runOpMode() {
        driveTrain.init(hardwareMap, config);

        DISTANCE_TO_FOUNDATION_X = config.getDouble("distance_to_foundation_x", 1200.2f - robotY);
        DISTANCE_TO_FOUNDATION_Y = config.getDouble("distance_to_foundation_y", halfCourt - 539.75f - 600.08f);
        TURNING_ANGLE = config.getDouble("turning_angle", 0);
        TURNING_RIGHT_ANGLE = config.getDouble("turning_right_angle", 90);
        SPEED = config.getDouble("speed", 1);

        waitForStart();

        while(opModeIsActive()){

            driveTrain.encoderDrive(SPEED, DISTANCE_TO_FOUNDATION_X, -DISTANCE_TO_FOUNDATION_Y, TURNING_ANGLE, 15);

            telemetry.update();
        }
    }
}
