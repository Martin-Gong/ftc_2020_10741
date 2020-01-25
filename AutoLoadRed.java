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
import java.util.Map;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Config;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "AutoLoadRed")
public class AutoLoadRed extends LinearOpMode {
    private ElapsedTime runTime = new ElapsedTime();
    DriveTrainByEncoder driveTrain = new DriveTrainByEncoder();
    private Config config = new Config(Config.configFile);

    private int DISTANCE_TO_SKYSTONE = 10;
    private int SKYSTONE_INTERVAL_DISTANCE = 10;
    private int BUILDING_SITE_DISTANCE = 200;

    @Override
    public void runOpMode() {
        driveTrain.init(hardwareMap,config);

    }
}
