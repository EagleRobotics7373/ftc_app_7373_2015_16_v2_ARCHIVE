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

package com.qualcomm.ftcrobotcontroller.opmodes.Alpha_bot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class Main_Robot_Auto_Red_NoDelay2 extends OpMode {

    //private String startDate;
    private ElapsedTime runtime = new ElapsedTime();

    DcMotor mright1;
    DcMotor mleft1;
    DcMotor mright2;
    DcMotor mleft2;

   // double x = 0;

    public void runfor(double seconds, double rpower, double lpower) {
        double x = this.time + seconds;
        while (this.time < x) {
            mleft1.setPower(lpower);
            mleft2.setPower(lpower);
            mright1.setPower(rpower);
            mleft2.setPower(rpower);
        }

    }

    @Override
    public void init() {
        //telemetry.addData("reached init_loop", 1);
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {
        //   telemetry.addData("reached line 1", 1);
        //  startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        //  telemetry.addData("reached line 2", 1);
        runtime.reset();
        // telemetry.addData("reached line 3", 1);
        //  telemetry.addData("Null Op Init Loop", runtime.toString());
        //  telemetry.addData("reached line 4", 1);
        mleft1 = hardwareMap.dcMotor.get("leftf");
        // telemetry.addData("reached line 5", 1);
        mleft2 = hardwareMap.dcMotor.get("leftr");
        //  telemetry.addData("reached line 6", 1);
        mright1 = hardwareMap.dcMotor.get("rightf");
        //   telemetry.addData("reached line 7", 1);
        mright2 = hardwareMap.dcMotor.get("rightr");
        //  telemetry.addData("reached line 8", 1);
        mleft1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //  telemetry.addData("reached line 9", 1);
        mleft2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        // telemetry.addData("reached line 10", 1);
        mright1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //  telemetry.addData("reached line 11", 1);
        mright2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */

    @Override
    public void loop() {
        //telemetry.addData("1 Start", "NullOp started at " + startDate);
        //  telemetry.addData("2 Status", "running for " + runtime.toString());

        runfor(1000, 1, 1);
        while (true) {

        }
    }
}

