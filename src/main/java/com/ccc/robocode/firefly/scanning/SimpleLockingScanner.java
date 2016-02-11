package com.ccc.robocode.firefly.scanning;

import com.ccc.robocode.firefly.Scanner;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * This implementation turns right until it sees the enemy and then  looks on 
 * the enemy.
 * 
 * @author bodo
 */
public class SimpleLockingScanner implements Scanner {
    
    private final AdvancedRobot robot;

    public SimpleLockingScanner(AdvancedRobot robot) {
        this.robot = robot;
    } 
    
    @Override
    public void scan() {
        robot.turnRadarRightRadians(Double.POSITIVE_INFINITY);
    }
    
    @Override
    public void scan(ScannedRobotEvent event) {
        robot.setTurnRadarLeftRadians(robot.getRadarTurnRemainingRadians());
    }

}
