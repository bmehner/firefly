package com.ccc.robocode.firefly;

import com.ccc.robocode.firefly.utils.BulletFiredDetector;
import com.ccc.robocode.firefly.movement.NinetyDegreesTowardsTarget;
import com.ccc.robocode.firefly.scanning.SimpleLockingScanner;
import com.ccc.robocode.firefly.targeting.LinearAdjustingTargeting;
import java.util.Random;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

/**
 * Robot for Robocode.
 * 
 * The name is inspired by the science fiction series "Firefly".
 * 
 * @see http://en.wikipedia.org/wiki/Firefly_%28TV_series%29
 * 
 * @author bodo
 */
public class Firefly extends AdvancedRobot {

    private final Random random;
    private final BulletFiredDetector bulletFiredDetector;
    private final Targeting targeting;
    private final Movement movement;
    private final Scanner scanner;

    public Firefly() {
        this.random = new Random();
        this.bulletFiredDetector = new BulletFiredDetector();
        this.targeting = new LinearAdjustingTargeting(this);
        this.movement = new NinetyDegreesTowardsTarget(this, random);
        this.scanner = new SimpleLockingScanner(this);
    }

    @Override
    public void run() {
        initializeFirefly();

        while (true) {
            scanner.scan();
        }

    }

    @Override
    public void onHitWall(HitWallEvent event) {
        movement.escapeWall(event);
    }

    @Override
    public String getName() {
        return "Firefly 1.0";
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        scanner.scan(event);

        if (bulletFiredDetector.hasEnemyFiredABullet(event)) {
            movement.dodgeBullet(event);
        } else {
            movement.move(event);
        }

        targeting.targetAndShoot(event);

    }

    private void initializeFirefly() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        double myHeading = getHeading();
        double myGunHeading = getGunHeading();
        turnGunRight(myHeading - myGunHeading);
    }
}
