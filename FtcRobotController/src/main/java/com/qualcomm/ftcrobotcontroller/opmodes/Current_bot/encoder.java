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

package com.qualcomm.ftcrobotcontroller.opmodes.Current_bot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class encoder extends OpMode {

    //variables are intiated
    DcMotor mleft1, mleft2, mright1, mright2;

    DcMotor conveyer;
    DcMotor intake;
    DcMotor arcreactor;
    // DcMotor pullup;
    Servo servor, servol;
    Servo hammer;
    Servo lefthand, righthand;

    double waitTime;

    @Override
    public void init() {
        //get references from hardware map
        mleft1 = hardwareMap.dcMotor.get("leftf");
        arcreactor = hardwareMap.dcMotor.get("arc");
        mleft2 = hardwareMap.dcMotor.get("leftr");
        mright1 = hardwareMap.dcMotor.get("rightf");
        mright2 = hardwareMap.dcMotor.get("rightr");
        intake = hardwareMap.dcMotor.get("intake");
        conveyer = hardwareMap.dcMotor.get("conveyer");
        servor = hardwareMap.servo.get("door_right");
        servol = hardwareMap.servo.get("door_left");
        //  pullup = hardwareMap.dcMotor.get("pullup");
        hammer = hardwareMap.servo.get("hammer");
        lefthand = hardwareMap.servo.get("left_hand");
        righthand = hardwareMap.servo.get("right_hand");
        //set dc motor modes to run with encoders and reset the encoders
        mleft1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mleft2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mright1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mright2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mleft1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mleft2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mright1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mright2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        hammer.setPosition(0);
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void start() {

        resetStartTime();



        //set motor run mode
        mright1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mright2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mleft1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mleft2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override

    public void loop() {

        waitTime = 2;

        if(mright2.getCurrentPosition() >= 11120 || mright2.getCurrentPosition() <= -11120 ) {

            intake.setPower(0f);
            mright1.setPower(0f);
            mleft1.setPower(0f);
            mright2.setPower(0f);
            mleft2.setPower(0f);
        }
        else {
            intake.setPower(-1f);
            mright1.setPower(1f);
            mleft1.setPower(1f);
            mright2.setPower(1f);
            mleft2.setPower(1f);
        }


        resetStartTime();
        waitTime = 3;

        while(waitTime < getRuntime()) {

            hammer.setPosition(0.8);
        }

        resetStartTime();
        waitTime = 2;

        while(waitTime < getRuntime()) {

            hammer.setPosition(0);


            mleft1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            mleft2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            mright1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            mright2.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        }

        if(mright2.getCurrentPosition() <= 1120 || mright2.getCurrentPosition() >= -1120 ) {
            mright1.setPower(0);
            mleft1.setPower(0);
            mright2.setPower(0);
            mleft2.setPower(0);
        }
        else {
            mright1.setPower(-1);
            mleft1.setPower(-1);
            mright2.setPower(-1);
            mleft2.setPower(-1);


    }

    }
}




