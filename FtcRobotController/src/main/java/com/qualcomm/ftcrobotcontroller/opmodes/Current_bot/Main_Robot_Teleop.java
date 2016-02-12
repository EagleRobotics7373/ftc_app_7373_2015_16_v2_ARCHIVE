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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class Main_Robot_Teleop extends OpMode {
    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();

    //variables are intiated
    DcMotor mleft1;
    DcMotor mleft2;
    DcMotor mright1;
    DcMotor mright2;
    DcMotor convayer;
    DcMotor intake;
    DcMotor arcreactor;
    DcMotor pullup;
    Servo servor;
    Servo servol;
    Servo Hamer;
    Servo lefthand;
    Servo righthand;
    // for drive ratio on controller 1
    int mode = 1;
    boolean d_up = gamepad1.dpad_up;
    boolean d_right = gamepad1.dpad_right;
    boolean d_down = gamepad1.dpad_down;
    boolean d_left = gamepad1.dpad_left;
    //for left and right bumpers of controller 2
    boolean convayerf;
    boolean convayerb;
    //for left and right triggers on controller 2
    float intakef;
    float intakeb;
    //left and right joystick on controller 1
    float right;
    float left;
    //left and right joystick on controller 2
    float left2;
    float right2;
    //set a and x on controller 1
    boolean b;
    boolean x;
    boolean y;
    boolean a;

    boolean rev = false;
    int k = 1;

    @Override
    public void init() {
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {
        startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        runtime.reset();

        //get references from hardware map.
        mleft1 = hardwareMap.dcMotor.get("leftf");
        arcreactor = hardwareMap.dcMotor.get("arc");
        mleft2 = hardwareMap.dcMotor.get("leftr");
        mright1 = hardwareMap.dcMotor.get("rightf");
        mright2 = hardwareMap.dcMotor.get("rightr");
        intake = hardwareMap.dcMotor.get("intake");
        convayer = hardwareMap.dcMotor.get("conveyer");
        servor = hardwareMap.servo.get("door_right");
        servol = hardwareMap.servo.get("door_left");
        pullup = hardwareMap.dcMotor.get("pullup");
        Hamer = hardwareMap.servo.get("hammer");
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
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override

    public void loop() {
        //reference telemetery
        telemetry.addData("1 Start", "TeleOP started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());
        if (gamepad1.y)
        {
            Hamer.setPosition(1);
        }
        if (gamepad1.a)
        {
            Hamer.setPosition(-.5);
        }
        if(gamepad1.left_bumper)
        {
            lefthand.setPosition(1);
        }
        if(gamepad1.left_trigger > .1)
        {
            lefthand.setPosition(-.5);
        }

        if(gamepad1.right_bumper)
        {
            lefthand.setPosition(1);
        }
        if(gamepad1.right_trigger > .1)
        {
            lefthand.setPosition(-.5);
        }

        //get gamepad position and set dpad vars accordingly
        if (gamepad1.dpad_up) {
            d_up = true;
            d_right = false;
            d_down = false;
            d_left = false;
        }
        if (gamepad1.dpad_right || gamepad1.dpad_left) {
            d_up = false;
            d_right = true;
            d_left = true;
            d_down = false;
        }
        if (gamepad1.dpad_up) {
            d_down = true;
            d_up = true;
            d_right = false;
            d_left = false;
        }

        //set speed ratio based on d_pad
        if (d_up) {
            mode = 1;
        }
        if (d_left || d_right) {
            mode = 2;
        }
        if (d_down) {
            mode = 3;
        }

        //get rev value
        if(gamepad1.right_bumper){
            rev = !rev;
        }
        if(rev){
            k = -1;
        } else {
            k = 1;
        }

        //get gamepad 1 joystick position and clip values
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        //drive system
        mleft1.setPower(-left*k);
        mleft2.setPower(-left*k);
        mright1.setPower(right*k);
        mright2.setPower(right*k);


        //get intake drive values
        intakef = gamepad2.right_trigger;
        intakeb = gamepad2.left_trigger;
        intakef = Range.clip(intakef, -1, 1);
        intakeb = Range.clip(intakeb, -1, 1);
        //intake system
        if (intakef > 0){
            intakeb = -intakef;
        }
        intake.setPower(intakeb);


        //conveyer system
        convayerf = gamepad2.right_bumper;
        convayerb = gamepad2.left_bumper;
        if (convayerf) {
            convayer.setPower(-1);
        } else if (convayerb) {
            convayer.setPower(1);
        } else {
            convayer.setPower(0);
        }

        //get servo bridges controller values
        b = gamepad1.b;
        x = gamepad1.x;
        y = gamepad1.y;
        a = gamepad1.a;

        //check the values and write to control bool
            // take control bool and write to servo
            if (b) {
                servor.setPosition(1);
            }
            if (y){
                servor.setPosition(.2);
            }
            if (x) {
                servol.setPosition(1);
            }
            if (a){
                servol.setPosition(.4);
            }


            //get gamepad 2 joystick values and clip ranges
            left2 = -gamepad2.right_stick_y;
            right2 = -gamepad2.left_stick_y;
            left2 = Range.clip(left2, -1, 1);
            right2 = Range.clip(right2, -1, 1);
            //Arc reactor write motor power scaled by half
            arcreactor.setPower(-right2);
            //PullUp write motor power scaled by half
            pullup.setPower(left2);

        }
    }

