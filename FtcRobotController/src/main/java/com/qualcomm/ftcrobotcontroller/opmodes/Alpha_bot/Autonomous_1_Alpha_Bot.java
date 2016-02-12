package com.qualcomm.ftcrobotcontroller.opmodes.Alpha_bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;


public class Autonomous_1_Alpha_Bot extends LinearOpMode {




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

    public void stopMotors() {
        mleft1.setPower(0.0);
        mleft2.setPower(0.0);
        mright1.setPower(0.0);
        mright2.setPower(0.0);
        intake.setPower(0);
    }

    public void resetDriveMotorEncoders(DcMotor mleft1, DcMotor mright1)
            throws InterruptedException
             {

                mleft1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                mright1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                mright1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                mleft1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
             }


    public void driveBackwardstoEncoderCount(int encoderCount){
        while(mleft1.getCurrentPosition() > encoderCount && mright1.getCurrentPosition() > encoderCount
                && this.opModeIsActive())
        {
            mleft1.setPower(-0.6);
            mleft2.setPower(-0.6);
            mright1.setPower(0.6);
            mright2.setPower(0.6);
        }

        mleft1.setPower(0.0);
        mleft2.setPower(0.0);
        mright1.setPower(0.0);
        mright2.setPower(0.0);
    }

    public void driveForwardtoEncoderCount(int encoderCount){
        while(mleft1.getCurrentPosition() < encoderCount && mright1.getCurrentPosition() < encoderCount
                && this.opModeIsActive())
            {
                mleft1.setPower(.6);
                mleft2.setPower(.6);
                mright1.setPower(-.6);
                mright2.setPower(-.6);
            }

        mleft1.setPower(0.0);
        mleft2.setPower(0.0);
        mright1.setPower(0.0);
        mright2.setPower(0.0);
    }

    public void rotateLeft(int degrees){
        while(mright1.getCurrentPosition() < degrees*13.6
                && this.opModeIsActive())
        {
            mleft1.setPower(-0.6);
            mleft2.setPower(-0.6);
            mright1.setPower(-0.6);
            mright2.setPower(-0.6);
        }

        mleft1.setPower(0.0);
        mleft2.setPower(0.0);
        mright1.setPower(0.0);
        mright2.setPower(0.0);
    }

    public void rotateRight(int degrees){
        while(mleft1.getCurrentPosition() < degrees*13.6
                && this.opModeIsActive())
        {
            mleft1.setPower(0.6);
            mleft2.setPower(0.6);
            mright1.setPower(0.6);
            mright2.setPower(0.6);
        }

        mleft1.setPower(0.0);
        mleft2.setPower(0.0);
        mright1.setPower(0.0);
        mright2.setPower(0.0);
    }




   @Override
   public void runOpMode() throws InterruptedException
    {

        //get references from hardware map
        arcreactor = hardwareMap.dcMotor.get("arc");
        mleft1 = hardwareMap.dcMotor.get("leftf");
        mleft2 = hardwareMap.dcMotor.get("leftr");
        mright1 = hardwareMap.dcMotor.get("rightf");
        mright2 = hardwareMap.dcMotor.get("rightr");
        intake = hardwareMap.dcMotor.get("intake");
        conveyer = hardwareMap.dcMotor.get("conveyer");
        servor = hardwareMap.servo.get("door_right");
        servol = hardwareMap.servo.get("door_left");
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

        //hammer.setPosition(0);
        righthand.setPosition(0.9);
        lefthand.setPosition(0);
        servor.setPosition(1); // close
        servol.setPosition(0); //close
        //resetDriveMotorEncoders(mright1, mleft1);

        //resetDriveMotorEncoders(mright1, mleft1);

        waitForStart();
        //sleep(500);


        for (int i = 0; i <1; i++)
            {
               // sleep(8000);

                intake.setPower(1);
                driveForwardtoEncoderCount(10500);


                sleep(500);
                stopMotors();
                sleep(500);

                hammer.setPosition(0.8);
                sleep(1000);

                hammer.setPosition(0);
                sleep(500);


                //resetDriveMotorEncoders(mright1, mleft1);
                //driveForwardtoEncoderCount(-3000);




            }
        stopMotors();


    }


}