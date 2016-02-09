/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes.Tiger_bot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class test_auto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //add motors
    DcMotor left;
    DcMotor right;


    //motor run class
    public void runmotors(DcMotor motor, double k, Boolean conditionmotor) {
        if (conditionmotor) {
            k = Range.clip(k, -1, 1);
            motor.setPower(k);
        }
    }

    public void runtoencoder(DcMotor motor1, int e){
        while(motor1.getCurrentPosition() <= e){

        }
    }

    public void resetencoder(){
        left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        runtime.reset();
        telemetry.addData("Null Op Init Loop", runtime.toString());
        //get motors from the hardware map
        left = hardwareMap.dcMotor.get("leftdrive");
        right = hardwareMap.dcMotor.get("rightdrive");
        //set motor channel mode
        left.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        right.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


        //wait for start signal from the coach
        waitForStart();

        //make initial forward move
        runmotors(left, 1, true);
        runmotors(right, -1, true);
        runtoencoder(left, 3000);
        waitOneFullHardwareCycle();

        //make turn
        runmotors(left, -1, true);
        runmotors(right, -1, true);
        runtoencoder(left, 3000);
        waitOneFullHardwareCycle();

        //go up mountain
        runmotors(left, 1, true);
        runmotors(right, -1, true);
        runtoencoder(left, 3000);
        waitOneFullHardwareCycle();




    }
}
