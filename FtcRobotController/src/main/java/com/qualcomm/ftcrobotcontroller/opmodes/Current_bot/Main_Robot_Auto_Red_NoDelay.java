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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class Main_Robot_Auto_Red_NoDelay extends OpMode {

    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();
    //initiate variables
    DcMotor mright1;
    DcMotor mleft1;
    DcMotor mright2;
    DcMotor mleft2;
    DcMotor arch;
    DcMotor intake;
    DcMotor convayer;
    DcMotor pullup;
    int mode = 1;

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

        //get references from hardware map
        mleft1 = hardwareMap.dcMotor.get("Motor Right");
        mleft2 = hardwareMap.dcMotor.get("Motor Left");
        intake = hardwareMap.dcMotor.get("Intake");
        mright1 = hardwareMap.dcMotor.get("m1");
        mright2 = hardwareMap.dcMotor.get("m2");
        convayer = hardwareMap.dcMotor.get("convayer");
        arch = hardwareMap.dcMotor.get("arch");
        pullup = hardwareMap.dcMotor.get("pullup");

        //set dc motor modes to run with encoders and reset the encoders
       /* mleft1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        mleft2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        mright1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        mright2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        mleft1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mleft2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mright1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mright2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
*/
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        //reference telemetery
        telemetry.addData("1 Start", "NullOp started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());

        //get gamepad position
        boolean d_up = gamepad1.dpad_up;
        boolean d_right = gamepad1.dpad_right;
        boolean d_down = gamepad1.dpad_down;
        boolean d_left = gamepad1.dpad_right;
        if (d_up == true)
        {
            mode = 1;

        }
        if(d_left == true || d_right == true)
        {
            mode = 2;
        }
        if(d_down == true)
        {
            mode = 3;
        }


        //get gamepad 1 joystick position and clip values
        float left = gamepad1.right_stick_y;
        float right = gamepad1.left_stick_y;
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        //drive system
        mleft1.setPower(-left/mode);
        mleft1.setPower(-left/mode);
        mright1.setPower(right/mode);
        mright1.setPower(right/mode);


        //get intake drive values
        boolean intakef = gamepad2.right_bumper;
        boolean intakeb = gamepad2.left_bumper;
        //intake system
        if(intakef)
        {
            intake.setPower(1);
        }
        if(intakeb)
        {
            intake.setPower(1);
        }


        //convayer system
        float convayerf = gamepad2.right_trigger;
        float convayerb = gamepad2.left_trigger;
        if(convayerf > 0)
        {
            convayer.setPower(1);
        }
        if(convayerb > 0)
        {
            convayer.setPower(-1);
        }


        //get gamepad 2 joystick values
        float left2 = gamepad2.right_stick_y;
        float right2 = -gamepad2.left_stick_y;
        //Arch reactor
        arch.setPower(right2/2);
        //PullUp
        pullup.setPower(left2/2);



    }
}
