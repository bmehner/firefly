package com.ccc.robocode.firefly.movement;

import com.ccc.robocode.firefly.Movement;
import java.util.Random;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 * This implementation keeps the robot rotated 90 degrees according to the 
 * target. This allows to dodge the bullets easy.
 * 
 * The dodging part is basically moving the robot randomly in a perpendicular 
 * manner according to the target.
 * 
 * If the robot hits a wall, the movement is inverted.
 * 
 * @author bodo
 */
public class NinetyDegreesTowardsTarget implements Movement {

    private final AdvancedRobot robot;
    private final Random random;

    public NinetyDegreesTowardsTarget(AdvancedRobot robot, Random random) {
        this.robot = robot;
        this.random = random;
    }

    @Override
    public void move(ScannedRobotEvent event) {
        double headOnBearingLafette = robot.getHeadingRadians() + event.getBearingRadians() + Math.PI / 2;
        robot.setTurnRightRadians(Utils.normalRelativeAngle(headOnBearingLafette - robot.getHeadingRadians()));
    }

    @Override
    public void escapeWall(HitWallEvent event) {
        if ((0 - Math.PI / 2) < event.getBearingRadians()
                && event.getBearingRadians() < (Math.PI / 2)) {
            robot.setBack(200);
        } else {
            robot.setAhead(200);
        }
    }

    @Override
    public void dodgeBullet(ScannedRobotEvent event) {
        boolean nextBoolean = random.nextBoolean();
        double distance = random.nextDouble() * 200;
        if (distance < 50) {
            distance = 100;
        }
        if (nextBoolean) {
            robot.setAhead(distance);
        } else {
            robot.setBack(distance);
        }
        
        move(event);
    }

}
