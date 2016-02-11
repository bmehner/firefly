package com.ccc.robocode.firefly.testutils;

import robocode.ScannedRobotEvent;

public abstract class ScanEventUtil {
    
    
    private ScanEventUtil() {
        super();
    }
    
    public static ScannedRobotEvent createScannedRobotEventWithBearing(double bearingInRadians) {
        ScannedRobotEvent event = new ScannedRobotEvent(TestConstants.TEST_NAME_ROBOT, TestConstants.TEST_ENERGY_ROBOT, bearingInRadians, TestConstants.TEST_DISTANCE_ROBOT_IN_PIXELS, TestConstants.TEST_HEADING_ROBOT, TestConstants.TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN, false);
        return event;
    }
    
    public static ScannedRobotEvent createScannedRobotEventWithEnergy(double energy) {
        ScannedRobotEvent event = new ScannedRobotEvent(TestConstants.TEST_NAME_ROBOT, 
                energy, TestConstants.TEST_BEARING_ROBOT_IN_RADIANS, TestConstants.TEST_DISTANCE_ROBOT_IN_PIXELS, TestConstants.TEST_HEADING_ROBOT, TestConstants.TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN, false);
        return event;
    }

}
