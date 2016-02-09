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


public class Teleop_rev1 extends OpMode {

  private String startDate;
  private ElapsedTime runtime = new ElapsedTime();

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
    telemetry.addData("Null Op Init Loop", runtime.toString());
  }

  /*
   * This method will be called repeatedly in a loop
   * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
   */
  @Override
  public void loop() {
    telemetry.addData("1 Start", "NullOp started at " + startDate);
    telemetry.addData("2 Status", "running for " + runtime.toString());
  }

  /**
   * TeleOp Mode
   * <p>
   *Enables control of the robot via the gamepad
   */
  public static class Main_Robot_Tele extends OpMode {
    //private String startDate;
   // private ElapsedTime runtime = new ElapsedTime();

    //get motors from hardware map
    DcMotor rightmotor; // Motor controller one Hardware map: "DR" function: Controls right drive motor
    DcMotor leftmotor; // Motor controller one Hardware map: "DL" function: Contorls left drive motor
    DcMotor scissor; // Motor controller two Hardware map: "scissor" function: Controls scissor lift lenght
    DcMotor turntable; // Motor controller two Hardware map: "turntable" function: Rotates turn turntable
    DcMotor spitch; // Motor controller three Hardware map: "spitch" function: Controls the rotation of the turn turntable
    DcMotor intake; // Motor controller three Hardware map: "intake" function: Controls intake motors
    DcMotor winch;

    //get servos from hardware map
    Servo lefttrigger;
    Servo righttrigger;
    Servo dropdown;
    Servo boxflap;
    Servo hammer;

    //variables
    double rpower;
    double lpower;
    double x = 1;

    @Override
    public void init() {
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {
      //startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
      //runtime.reset();

      //get references from hardware map.
      rightmotor = hardwareMap.dcMotor.get("rightmotor");
      leftmotor = hardwareMap.dcMotor.get("leftmotor");
      scissor = hardwareMap.dcMotor.get("scissor");
      turntable = hardwareMap.dcMotor.get("turntable");
      spitch = hardwareMap.dcMotor.get("spitch");
      intake = hardwareMap.dcMotor.get("intake");
      lefttrigger = hardwareMap.servo.get("lefttrigger");
      righttrigger = hardwareMap.servo.get("righttrigger");
      dropdown = hardwareMap.servo.get("dropdown");
      boxflap = hardwareMap.servo.get("boxflap");
      hammer = hardwareMap.servo.get("hammer");


      //Reset encoder
      rightmotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      leftmotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      scissor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      turntable.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      spitch.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      intake.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      // Allow motors to use encoders
      leftmotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
      scissor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
      turntable.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
      spitch.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
      intake.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */



    public void set_drive_train()
    {
      //aquire data from gamepad
      rpower = -gamepad1.right_stick_y;
      lpower = -gamepad1.left_stick_y;
      // Make sure the data is within -1 through 1
      rpower = Range.clip(rpower, -1, 1);
      lpower = Range.clip(lpower, -1, 1);
      //Set power for drive motors
      rightmotor.setPower(rpower);
      rightmotor.setPower(lpower);
    }

    //motor run class
    public void runmotor(DcMotor motor, double k, Boolean conditionmotor){
      if(conditionmotor) {
        k = Range.clip(k, -1, 1);
        motor.setPower(k);
      }
    }


    //servo run class
    public void setservo (Servo servo, double p, Boolean conditionservo) {
      if (conditionservo) {
        p = Range.clip(p, 0, 1);
        servo.setPosition(p);
      }
    }

    public void resetencoders () {
      //reset encoders
      rightmotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      leftmotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      scissor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      turntable.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      spitch.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      intake.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    //class to reset the whole robot
  public void reset() {
    //reset servo
    boxflap.setPosition(0);
    dropdown.setPosition(0);
    lefttrigger.setPosition(0);
    righttrigger.setPosition(0);

    //reset motor power
    rightmotor.setPower(0);
    leftmotor.setPower(0);
    scissor.setPower(0);
    turntable.setPower(0);
    spitch.setPower(0);
    intake.setPower(0);

    //reset encoders
    rightmotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    leftmotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    scissor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    turntable.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    spitch.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    intake.setMode(DcMotorController.RunMode.RESET_ENCODERS);
  }



    @Override
    public void loop() {
      //run the drive train
      set_drive_train();

      //drop the intake and run it
      setservo(dropdown, 1, gamepad2.a);
      setservo(dropdown, 0, gamepad2.y);
      runmotor(intake, gamepad2.right_trigger, true);
      runmotor(intake, -gamepad2.left_trigger, true);

      //run the scissor
      runmotor(scissor, gamepad2.left_stick_y, gamepad2.y);

      //pitch and turn scissor
      runmotor(spitch, -gamepad2.right_stick_y, true);
      runmotor(turntable, gamepad2.right_stick_x, true);

     //run the winch
      runmotor(winch, -gamepad2.right_stick_y, true);

      //run the zipliner triggers
      setservo(lefttrigger, 1, gamepad1.x);
      setservo(righttrigger, 1, gamepad1.b);
      setservo(lefttrigger, 0, gamepad1.y);
      setservo(righttrigger, 0, gamepad1.y);

      //open and close the box flap
      setservo(boxflap, 1, gamepad1.dpad_down);
      setservo(boxflap, .5, gamepad1.dpad_left);
      setservo(boxflap, .5, gamepad1.dpad_right);
      setservo(boxflap, 0, gamepad1.dpad_up);

      //throw the climbers into the box
      setservo(hammer, 1, gamepad2.dpad_up);
      setservo(hammer, 0, gamepad2.dpad_down);

    }
  }
}
