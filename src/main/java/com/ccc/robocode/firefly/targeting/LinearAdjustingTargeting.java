package com.ccc.robocode.firefly.targeting;

import com.ccc.robocode.firefly.Targeting;
import robocode.AdvancedRobot;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 *
 * @author bodo
 */
public class LinearAdjustingTargeting implements Targeting {
    
    private static final double DEFAULT_BULLET_POWER = 3;
    
    private final AdvancedRobot robot;
    private double actualBulletPower = DEFAULT_BULLET_POWER;

    public LinearAdjustingTargeting(AdvancedRobot robot) {
        this.robot = robot;    
    }
    
    @Override
    public void targetAndShoot(ScannedRobotEvent event) {
        double headOnBearing = robot.getHeadingRadians() + event.getBearingRadians();
        double linearBearing = headOnBearing + Math.asin(event.getVelocity() / Rules.getBulletSpeed(actualBulletPower) * Math.sin(event.getHeadingRadians() - headOnBearing));
        robot.setTurnGunRightRadians(Utils.normalRelativeAngle(linearBearing - robot.getGunHeadingRadians()));
        robot.setFire(actualBulletPower);
    }

}
