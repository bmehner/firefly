package com.ccc.robocode.firefly.testutils;

import robocode.ScannedRobotEvent;

public abstract class ScanEventUtil {
    
    public static final String TEST_NAME_ROBOT = "TestRobot";
    public static final double TEST_ENERGY_ROBOT = 1;
    public static final double TEST_BEARING_ROBOT_IN_RADIANS = 0;
    public static final double TEST_DISTANCE_ROBOT_IN_PIXELS = 100;
    public static final double TEST_HEADING_ROBOT = 1;
    public static final double TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN = 0;
    
    private ScanEventUtil() {
        super();
    }
    
    public static ScannedRobotEvent createScannedRobotEventWithBearing(double bearingInRadians) {
        ScannedRobotEvent event = new ScannedRobotEvent(TEST_NAME_ROBOT, 
                TEST_ENERGY_ROBOT, bearingInRadians, 
                TEST_DISTANCE_ROBOT_IN_PIXELS, TEST_HEADING_ROBOT, 
                TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN, false);
        return event;
    }
    
    public static ScannedRobotEvent createScannedRobotEventWithEnergy(double energy) {
        ScannedRobotEvent event = new ScannedRobotEvent(TEST_NAME_ROBOT, 
                energy, TEST_BEARING_ROBOT_IN_RADIANS, 
                TEST_DISTANCE_ROBOT_IN_PIXELS, TEST_HEADING_ROBOT, 
                TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN, false);
        return event;
    }

}
