package com.ccc.robocode.firefly.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import robocode.ScannedRobotEvent;

/**
 * Detects if a robot has fired since the last scan. 
 * Is able to keep records of multiple robots.
 *
 * @author bodo
 */
public class BulletFiredDetector {

    private final Map<String, ScannedRobotEvent> historicRobotData;

    public BulletFiredDetector() {
        this.historicRobotData = new ConcurrentHashMap<>();
    }

    public boolean hasEnemyFiredABullet(ScannedRobotEvent event) {
        boolean hasRobotFired;
        double currentEnemyEnergy = event.getEnergy();
        ScannedRobotEvent oldEvent = historicRobotData.get(event.getName());
        if (oldEvent != null) {
            double oldEnemyEnergy = oldEvent.getEnergy();
            double diffInEnemyEnergyLevel = oldEnemyEnergy - currentEnemyEnergy;
            hasRobotFired = diffInEnemyEnergyLevel > 0.1 && diffInEnemyEnergyLevel < 4;
        } else {
            hasRobotFired = false;
        }

        historicRobotData.put(event.getName(), event);
        
        return hasRobotFired;
    }

}
