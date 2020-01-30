package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "AutoBuildBlue")
@Disabled
public class AutoBuildBlue extends LinearOpMode{

    private DriveTrainEncoder driveTrain = new DriveTrainEncoder();
    private int robotX;
    private int robotY;

    @Override
    public void runOpMode() {
        driveTrain.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            driveTrain.encoderDrive();




            telemetry.update();
        }
    }
}
