package com.ccc.robocode.firefly;

import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

/**
 * Provides a strategy for moving the robot in general and in accordance to some
 * events.
 * 
 * @author bodo
 */
public interface Movement {

    void move(ScannedRobotEvent event);
    
    void escapeWall(HitWallEvent event);
    
    void dodgeBullet(ScannedRobotEvent event);
    
}
