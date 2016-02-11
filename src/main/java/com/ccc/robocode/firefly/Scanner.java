package com.ccc.robocode.firefly;

import robocode.ScannedRobotEvent;

/**
 * A strategy that controls the scanner of the robot.
 * 
 * @author bodo
 */
public interface Scanner {

    void scan();

    void scan(ScannedRobotEvent event);
    
}
