package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;




@TeleOp
public class ServoTester extends LinearOpMode {


    public Servo Triangles;
    public Servo Rotate;




    @Override
    public void runOpMode() {
        Triangles = hardwareMap.get(Servo.class, "Triangles");
        Triangles.setDirection(Servo.Direction.FORWARD);
        //pod2 reverse
        //pod3forward
        Triangles.scaleRange(0.0, 1.0);
        Triangles.setPosition(0.0);


        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.a){
                Triangles.setPosition(0.0);
            }
            if (gamepad1.b) {
                Triangles.setPosition(0.3);
            }
            if(gamepad1.x){

            }
        }


    }
}
