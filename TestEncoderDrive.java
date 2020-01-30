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
import org.firstinspires.ftc.teamcode.util.ButtonHelper;
import org.firstinspires.ftc.teamcode.util.Config;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.teamcode.util.telemetry.TelemetryWrapper;

@TeleOp(name = "EncoderDrive Test",group = "Final")
//@Disabled
public class TestEncoderDrive extends LinearOpMode {
    //Import config file
    private Config config = new Config(Config.configFile);

    //Initializing runtime for timeViewing
    private ElapsedTime runtime = new ElapsedTime();

    //Declaring buttonHelpers
    private ButtonHelper helper;
    private ButtonHelper helper2;

    //Declaring control classes
    DriveTrainByEncoder driveTrainByEncoder = new DriveTrainByEncoder();

    //OpMode Version
    private final String OPMODE_VERSION = "2.0";

    static double miliToRunPerClick = 200.0;

    @Override
    public void runOpMode() {
        driveTrainByEncoder.init(hardwareMap,config);
        driveTrainByEncoder.stop();

        helper = new ButtonHelper(gamepad1);

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

            if(helper.pressing(ButtonHelper.dpad_up)) {
                driveTrainByEncoder.encoderDrive(0.5,0,miliToRunPerClick,0.0,3);
            }
            else if(helper.pressing(ButtonHelper.dpad_left)) {
                driveTrainByEncoder.encoderDrive(0.5,-miliToRunPerClick,0.0,0.0,3);
            }
            else if(helper.pressing(ButtonHelper.dpad_right)) {
                driveTrainByEncoder.encoderDrive(0.5,miliToRunPerClick,0.0,0.0,3);
            }
            else if(helper.pressing(ButtonHelper.dpad_down)) {
                driveTrainByEncoder.encoderDrive(0.5,0,-miliToRunPerClick,0.0,3);
            }

            if(helper.pressing(ButtonHelper.b)) {
                miliToRunPerClick += 50;
            }
            else if(helper.pressing(ButtonHelper.x)) {
                miliToRunPerClick -= 50;
            }


            //Updating all variable information to Telemetry Display
            TelemetryWrapper.setLine(0, "TeleOpMode v" + OPMODE_VERSION + "  Time Elapsed[s]: " + (runtime.milliseconds()/1000));
            TelemetryWrapper.setLine(1, "Encoder Data: TicksPR=" + driveTrainByEncoder.COUNTS_PER_MOTOR_REV);

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
