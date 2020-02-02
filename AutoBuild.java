package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.util.Config;

@Autonomous(name = "AutoBuild")
public class AutoBuild extends LinearOpMode {
    private ElapsedTime runTime = new ElapsedTime();
    DriveTrainByEncoder driveTrain = new DriveTrainByEncoder();
    private Config config = new Config(Config.configFile)  ;

    //    private Grabber grabber = new Grabber();
//    private ElevatorNoEnc elevator = new ElevatorNoEnc();

//    private

//    private static final float mmPerInch     = 25.4f;
//    private static final float robotX        = 17 * mmPerInch;
//    private static final float robotY        = 14.5f * mmPerInch;
//    private static final float halfCourt = 23.625f * mmPerInch;
//    private double DISTANCE_TO_FOUNDATION_X = 1200.2f - robotY;
//    private double DISTANCE_TO_FOUNDATION_Y = halfCourt - 539.75f - 600.08f;
//    private double TURNING_ANGLE = 0;
//    private double TURNING_RIGHT_ANGLE = 90;
//    private double SPEED = 1.0;

//        DISTANCE_TO_FOUNDATION_X = config.getDouble("distance_to_foundation_x", 1200.2f - robotY);
//        DISTANCE_TO_FOUNDATION_Y = config.getDouble("distance_to_foundation_y", halfCourt - 539.75f - 600.08f);
//        TURNING_ANGLE = config.getDouble("turning_angle", 0);
//        TURNING_RIGHT_ANGLE = config.getDouble("turning_right_angle", 90);
//        SPEED = config.getDouble("speed", 1);

    private static double DISTANCE_TO_LINE = 100;

    @Override
    public void runOpMode() {
        driveTrain.init(hardwareMap,config);
        driveTrain.stop();

        DISTANCE_TO_LINE = config.getDouble("distance_to_line_from_build",10);

        waitForStart();

        driveTrain.encoderDrive(0.8,0.0,DISTANCE_TO_LINE,0.0,10);
    }
}
