package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(6)
            .forwardZeroPowerAcceleration(-48.736112599598655)
            .lateralZeroPowerAcceleration(-52.04372307068255)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.05, 0, 0, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(1,0,0,.01))
        .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.03,0.0,0.00,0.6,0.0));


    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .xVelocity(84.64568082554135)
            .yVelocity(75.1053625392163)
            .rightFrontMotorName("RF")
            .rightRearMotorName("RR")
            .leftRearMotorName("LR")
            .leftFrontMotorName("LF")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);


    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-1)
            .strafePodX(0.5)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("Pin")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();

    }
}
