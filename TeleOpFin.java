/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.util.ButtonHelper;
import org.firstinspires.ftc.teamcode.util.Config;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.teamcode.util.telemetry.TelemetryWrapper;

/**
 * @class OpMode class for team 10741 for FTC
 * @author Wyvern github: https://github.com/Martin-Gong
 * @version 4.0
 */

@TeleOp(name = "TeleOp v4.0",group = "Final")
//@Disabled
public class TeleOpFin extends LinearOpMode {
    //Import config file
    private Config config = new Config(Config.configFile);

    //Initializing runtime for timeViewing
    private ElapsedTime runtime = new ElapsedTime();

    //Declaring buttonHelpers
    private ButtonHelper helper;
    private ButtonHelper helper2;

    //Runtime variables
    private double MOTOR_SPEED_MULTIPLIER = 1.0;
    static boolean clawStateOpened = true;
    final boolean USE_ENCODER_FOR_ELEVATOR = false;

    //OpMode Version
    private final String OPMODE_VERSION = "4.0";

    @Override
    public void runOpMode() {
        //Setting up button helpers
        helper = new ButtonHelper(gamepad1);
        helper2 = new ButtonHelper(gamepad2);

        Elevator elevator;
        ElevatorNoEnc elevatorNoEnc;
        DriveTrain driveTrain;
        Grabber grabber;

        //Declaring functionality classes
        if(USE_ENCODER_FOR_ELEVATOR) elevator = new Elevator();
        else elevatorNoEnc = new ElevatorNoEnc();
        driveTrain = new DriveTrain();
        grabber = new Grabber();

        //Loading configurations
        MOTOR_SPEED_MULTIPLIER = config.getDouble("motor_speed_multiplier",1.0);

        //Initializing functionality classes
        driveTrain.init(hardwareMap,config);
        grabber.init(hardwareMap,config);
        if(USE_ENCODER_FOR_ELEVATOR)
            elevator.init(hardwareMap,config);
        else
            elevatorNoEnc.init(hardwareMap,config);

        driveTrain.stop();

        //Configuring elevator initial positions
        grabber.openGrabber();
        if(USE_ENCODER_FOR_ELEVATOR) elevator.setLiftZeroPosition();

        //Initializing TelemetryWrapper
        TelemetryWrapper.init(telemetry,11);

        // Wait for the start button
        telemetry.addData(">>>", "Press to start TeleOp Mode" );
        telemetry.update();
        waitForStart();
        runtime.reset();

        //OpMode loop
        while(opModeIsActive()){
            //Updating buttonHelpers
            helper.update();
            helper2.update();

            //Get values for movement
            double drivey =  -gamepad1.right_stick_y*MOTOR_SPEED_MULTIPLIER;
            double drivex =  -gamepad1.right_stick_x*MOTOR_SPEED_MULTIPLIER;
            double turn  =  gamepad1.left_stick_x*MOTOR_SPEED_MULTIPLIER;

            //Movement control
            if (Math.abs(drivey) > 0.01 || Math.abs(drivex) > 0.01 || Math.abs(turn) > 0.01) {
                driveTrain.move(drivex, drivey, turn);
            }
            else {
                drivey = -gamepad2.right_stick_y * 0.25;
                drivex = -gamepad2.right_stick_x * 0.25;
                turn = gamepad2.left_stick_x * 0.25;
                driveTrain.move(drivex, drivey, turn);
            }

            //Elevator control with encoder and w/o encoder
            if(USE_ENCODER_FOR_ELEVATOR) {
                if(gamepad1.dpad_up) {
                    elevator.moveUp();
                }
                else if(gamepad1.dpad_down) {
                    elevator.moveDown();
                }
            }
            else {
                if(helper.pressed(ButtonHelper.right_bumper)||helper2.pressed(ButtonHelper.right_bumper)) {
                    elevatorNoEnc.moveUp();
                }
                else if(helper.pressed(ButtonHelper.left_bumper)||helper2.pressed(ButtonHelper.left_bumper)) {
                    elevatorNoEnc.moveDown();
                }
                else {
                    elevatorNoEnc.stop();
                }
            }

            //Claw/Grabber value change
            if(helper.pressing(ButtonHelper.x)||helper2.pressing(ButtonHelper.x)) {
                clawStateOpened =! clawStateOpened;
            }

            if(helper.pressing(ButtonHelper.start)||helper.pressing(ButtonHelper.start)) driveTrain.stop();

//            if(helper2.pressing(ButtonHelper.x)) {
//                grabber.netIncreasePos(0.1);
//            }

            //Claw/Grabber position execution
            if(clawStateOpened) {
                grabber.openGrabber();
            }
            else {
                grabber.closeGrabber();
            }

            double fl_p = Range.clip(drivey-drivex+turn,-1,1);
            double fr_p = Range.clip(drivey+drivex-turn,-1,1);
            double rl_p = Range.clip(drivey+drivex+turn,-1,1);
            double rr_p = Range.clip(drivey-drivex-turn,-1,1);

            //Updating all variable information to Telemetry Display
            TelemetryWrapper.setLine(0, "TeleOpMode v" + OPMODE_VERSION + "  Time Elapsed[s]: " + (runtime.milliseconds()/1000));
            TelemetryWrapper.setLine(1, "Motors Multiplier=" + MOTOR_SPEED_MULTIPLIER);
            TelemetryWrapper.setLine(2,"Motors: drivex=" + drivex +" drivey=" + drivey+" turn="+turn);
            TelemetryWrapper.setLine(3, "Pos Open/Closed=" + grabber.GRABBER_OPENED_POSITION + "/" + grabber.GRABBER_CLOSED_POSITION + " servoOpened=" + clawStateOpened);
            TelemetryWrapper.setLine(4,"Elevator Power: " + elevatorNoEnc.LIFT_POWER);
            TelemetryWrapper.setLine(5,"DriveTrain Adjusts: FL=" + driveTrain.FL_DRIVE_ADJUST + " FR=" + driveTrain.FR_DRIVE_ADJUST + " RL=" + driveTrain.RL_DRIVE_ADJUST + " RR=" + driveTrain.RR_DRIVE_ADJUST);
            TelemetryWrapper.setLine(6,"Motor actual input power: fl="+fl_p+" fr="+fr_p+" rl="+rl_p+" rr="+rr_p);

            idle();
        }

        //Final output
        telemetry.addData(">", "Done");
        telemetry.update();
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
