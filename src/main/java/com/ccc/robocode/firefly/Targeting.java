package com.ccc.robocode.firefly;

import robocode.ScannedRobotEvent;

/**
 * A strategy that controls the robots gun.
 * 
 * @author bodo
 */
public interface Targeting {

    void targetAndShoot(ScannedRobotEvent event);
    
}
