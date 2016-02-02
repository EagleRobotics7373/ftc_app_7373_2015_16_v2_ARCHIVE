package com.qualcomm.ftcrobotcontroller.opmodes.Current_bot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;


public class Autonomous_1_Alpha_Bot extends OpMode {

    //variables are intiated
    DcMotor mleft1;
    DcMotor mleft2;
    DcMotor mright1;
    DcMotor mright2;
    DcMotor conveyer;
    DcMotor intake;
    DcMotor arcreactor;
    // DcMotor pullup;
    Servo servor;
    Servo servol;
    Servo hammer;
    Servo lefthand;
    Servo righthand;

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


    @Override
    public void start() {

        //set motor run mode
        mright1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mright2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mleft1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        mleft2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


    }


    @Override

    public void loop () {

        if (mright2.getCurrentPosition() >= 11120 || mright2.getCurrentPosition() <= -11120) {
            mright1.setPower(0);
            mleft1.setPower(0);
            mright2.setPower(0);
            mleft2.setPower(0);
        } else {
            mright1.setPower(1);
            mleft1.setPower(1);
            mright2.setPower(1);
            mleft2.setPower(1);
        }

        // wait(1000);

        hammer.setPosition(0.8);

        //wait(2000);

        hammer.setPosition(0);

        mleft1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mleft2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mright1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        mright2.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        // wait(1000);

        if (mright2.getCurrentPosition() <= 1120 || mright2.getCurrentPosition() >= -1120) {
            mright1.setPower(0);
            mleft1.setPower(0);
            mright2.setPower(0);
            mleft2.setPower(0);
        } else {
            mright1.setPower(-1);
            mleft1.setPower(-1);
            mright2.setPower(-1);
            mleft2.setPower(-1);


        }

    /*public void stop () {
        mright1.setPower(0);
        mleft1.setPower(0);
        mright2.setPower(0);
        mleft2.setPower(0);
    }
    */
}
}