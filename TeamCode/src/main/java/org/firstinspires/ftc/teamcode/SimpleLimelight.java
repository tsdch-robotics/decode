package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.geometry.Pose; // Import Pedro's Pose
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import java.util.List;

public class SimpleLimelight {

    private Limelight3A limelight;

    public SimpleLimelight(HardwareMap hwMap) {
        limelight = hwMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(100);
        limelight.start();
    }

    // This function returns "null" if it can't see a tag, or a valid Pedro Pose if it can.
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

    public boolean isTagVisible(int targetId) {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
            for (LLResultTypes.FiducialResult tag : fiducials) {
                if (tag.getFiducialId() == targetId) {
                    return true;
                }
            }
        }
        return false;
    }


    public int getTagID() {
        LLResult result = limelight.getLatestResult();
        // Check if result is valid
        if (result == null || !result.isValid()) {
            return -1;
        }

        List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();

        // Loop through them to find our specific targets
        for (LLResultTypes.FiducialResult tag : tags) {
            int id = (int) tag.getFiducialId();
            if (id >= 20 && id <= 24) {
                return id;
            }
        }

        return -1;
    }


    public void setPipeline(int index) {
        limelight.pipelineSwitch(index);
    }

    public void stop() {
        limelight.stop();
    }
}