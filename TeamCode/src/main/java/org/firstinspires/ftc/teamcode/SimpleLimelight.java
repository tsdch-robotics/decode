package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.geometry.Pose; // Import Pedro's Pose
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class SimpleLimelight {

    private Limelight3A limelight;

    public SimpleLimelight(HardwareMap hwMap) {
        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(100);
        limelight.start();
    }

    // This function does all the math for you.
    // It returns "null" if it can't see a tag, or a valid Pedro Pose if it can.
    public Pose getPedroPose() {
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid()) {
            Pose3D botpose = result.getBotpose_MT2(); // Use MegaTag2 for best results

            if (botpose != null) {
                // Convert Meters (Limelight) to Inches (Pedro)
                double xInches = botpose.getPosition().x * 39.3701;
                double yInches = botpose.getPosition().y * 39.3701;
                double headingRad = botpose.getOrientation().getYaw(AngleUnit.RADIANS);

                return new Pose(xInches, yInches, headingRad);
            }
        }
        return null;
    }

    public void stop() {
        limelight.stop();
    }
}