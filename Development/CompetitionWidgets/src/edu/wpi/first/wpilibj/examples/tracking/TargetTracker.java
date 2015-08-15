package edu.wpi.first.wpilibj.examples.tracking;

import java.util.List;

/**
 *
 * @author robotics
 */
public interface TargetTracker {

	public void update(List<Target> knownTargets, List<FrameResults> frames);

}
