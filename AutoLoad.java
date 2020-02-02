package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.util.Config;

@Autonomous(name = "AutoLoad")
public class AutoLoad extends LinearOpMode {
    private ElapsedTime runTime = new ElapsedTime();
    DriveTrainByEncoder driveTrain = new DriveTrainByEncoder();
    private Config config = new Config(Config.configFile)  ;

    private static double DISTANCE_TO_APPROACH = 200;

    @Override
    public void runOpMode() {
        driveTrain.init(hardwareMap,config);
        driveTrain.stop();

        DISTANCE_TO_APPROACH = config.getDouble("distance_to_line",10);

        waitForStart();

        driveTrain.encoderDrive(0.8,0.0,DISTANCE_TO_APPROACH,0.0,10);
    }
}
