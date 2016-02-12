

package com.qualcomm.ftcrobotcontroller.opmodes.Alpha_bot;

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
//
public class Main_Robot_Teleop extends OpMode {
    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();

    //variables are intiated
    DcMotor mleft1;
    DcMotor mleft2;
    DcMotor mright1;
    DcMotor mright2;
    DcMotor conveyer;
    DcMotor intake;
    DcMotor arcreactor;
    DcMotor pullup;
    Servo servor;
    Servo servol;
    Servo hammer;
    Servo lefthand;
    Servo righthand;
    // for drive ratio on controller 1
    int mode = 1;
    boolean d_up = gamepad1.dpad_up;
    boolean d_right = gamepad1.dpad_right;
    boolean d_down = gamepad1.dpad_down;
    boolean d_left = gamepad1.dpad_left;
    //for left and right bumpers of controller 2
    boolean conveyerf;
    boolean conveyerb;
    //for left and right triggers on controller 2
    float intakef;
    float intakeb;
    //left and right joystick on controller 1
    float right1;
    float left1;
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
   // boolean first_pass = true;



    @Override
    public void init() {


    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {
       // startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        runtime.reset();

        //get references from hardware map.
        mleft1 = hardwareMap.dcMotor.get("leftf");
        arcreactor = hardwareMap.dcMotor.get("arc");
        mleft2 = hardwareMap.dcMotor.get("leftr");
        mright1 = hardwareMap.dcMotor.get("rightf");
        mright2 = hardwareMap.dcMotor.get("rightr");
        intake = hardwareMap.dcMotor.get("intake");
        conveyer = hardwareMap.dcMotor.get("conveyer");
        servor = hardwareMap.servo.get("door_right");
        servol = hardwareMap.servo.get("door_left");
        pullup = hardwareMap.dcMotor.get("pullup");
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


        righthand.setPosition(0.9);
        lefthand.setPosition(0);
        servor.setPosition(1); // close
        servol.setPosition(0); //close



    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override

    public void loop()
    {
        //reference telemetery
        telemetry.addData("1 Start", "TeleOP started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());


        if (gamepad1.y)
        {
            hammer.setPosition(0.8);
        }
        if (gamepad1.a)
        {
            hammer.setPosition(0);
        }

        if(gamepad1.left_bumper)
        {
            lefthand.setPosition(0); // closed
        }
        if(gamepad1.left_trigger > .1)
        {
            lefthand.setPosition(0.65); // open
        }

        if(gamepad1.right_bumper)
        {
            righthand.setPosition(0.9); // closed
        }
        if(gamepad1.right_trigger > .1)
        {
            righthand.setPosition(0.10); // open
        }

        //get gamepad position and set dpad vars accordingly
/*
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

        /*get rev value
        if(gamepad1.right_bumper){
            rev = !rev;
        }
        if(rev){
            k = -1;
        } else {
            k = 1;
        }
        */

        //get gamepad 1 joystick position and clip values
        left1 = gamepad1.left_stick_y;
        right1 = gamepad1.right_stick_y;
        right1 = Range.clip(right1, -1, 1);
        left1 = Range.clip(left1, -1, 1);

        //drive system
        if(Math.abs(gamepad1.left_stick_y) > 0.05 || Math.abs(gamepad1.right_stick_y) > 0.05)
        {
            mleft1.setPower(-left1);  // front left motor
            mleft2.setPower(-left1);  // rear left motor
            mright1.setPower(right1);  // front right motor
            mright2.setPower(right1);  // rear right motor
        }
        else
        {
            mleft1.setPower(0);  // front left motor
            mleft2.setPower(0);  // rear left motor
            mright1.setPower(0);  // front right motor
            mright2.setPower(0);  // rear right motor
        }

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
        conveyerf = gamepad2.right_bumper;
        conveyerb = gamepad2.left_bumper;
        if (conveyerf) {
            conveyer.setPower(-1);
        } else if (conveyerb) {
            conveyer.setPower(1);
        } else {
            conveyer.setPower(0);
        }

        //get servo bridges controller values
        b = gamepad2.b;
        x = gamepad2.x;
        y = gamepad2.y;
        a = gamepad2.a;

        //check the values and write to control bool
            // take control bool and write to servo

           // right door
            if (b) {
                servor.setPosition(0.50); // open
            }
            if (y){
                servor.setPosition(1); // close
            }

        // left door
            if (x) {
                servol.setPosition(0); //close
            }
            if (a){
                servol.setPosition(0.50); // open
            }

        //macro for scoring
        if (gamepad2.dpad_left){ //if dpad left then score to the left
            servol.setPosition(.5);
            conveyer.setPower(1);
        } else if(gamepad2.dpad_right){ //if dpad right then score to the right
            servor.setPosition(.5);
            conveyer.setPower(-1);
        } else if (gamepad2.dpad_down){
            servor.setPosition(0);
            servol.setPosition(1);
            conveyer.setPower(0);
        } else {

        }


            //get gamepad 2 joystick values and clip ranges
            right2 = -gamepad2.right_stick_y;
            left2 = -gamepad2.left_stick_y;
            left2 = Range.clip(left2, -1, 1);
            right2 = Range.clip(right2, -1, 1);
            //Arc reactor write motor power scaled by half
            arcreactor.setPower(left2);
            //PullUp write motor power scaled by half
            pullup.setPower(right2);

        }
    }

