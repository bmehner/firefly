/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ccc.robocode.firefly.utils;

import com.ccc.robocode.firefly.testutils.ScanEventUtil;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.hamcrest.CoreMatchers.*;
import org.mockito.runners.MockitoJUnitRunner;
import robocode.ScannedRobotEvent;

public class BulletFiredDetectorTest {
    
    private BulletFiredDetector bulletFiredDetector;

    public BulletFiredDetectorTest() {
        bulletFiredDetector = new BulletFiredDetector();
    }
    
    @Test
    public void testHasEnemyFiredABulletReturnsFalseWhenNoPreviousData() {
        ScannedRobotEvent event = ScanEventUtil.createScannedRobotEventWithBearing(10);
        assertThat(bulletFiredDetector.hasEnemyFiredABullet(event), equalTo(false));
    }
    
    @Test
    public void testHasEnemyFiredABulletReturnsFalseWhenPreviousDataIsEqual() {
        ScannedRobotEvent event = ScanEventUtil.createScannedRobotEventWithBearing(10);
        bulletFiredDetector.hasEnemyFiredABullet(event);
        assertThat(bulletFiredDetector.hasEnemyFiredABullet(event), equalTo(false));
    }
    
    @Test
    public void testHasEnemyFiredABulletReturnsTrueWhenEnergyDropIsOne() {
        ScannedRobotEvent event = ScanEventUtil.createScannedRobotEventWithEnergy(10);
        bulletFiredDetector.hasEnemyFiredABullet(event);
        event = ScanEventUtil.createScannedRobotEventWithEnergy(9);
        assertThat(bulletFiredDetector.hasEnemyFiredABullet(event), equalTo(true));
    }
    
    @Test
    public void testHasEnemyFiredABulletReturnsFalseWhenEnergyDropIsFive() {
        ScannedRobotEvent event = ScanEventUtil.createScannedRobotEventWithEnergy(10);
        bulletFiredDetector.hasEnemyFiredABullet(event);
        event = ScanEventUtil.createScannedRobotEventWithEnergy(5);
        assertThat(bulletFiredDetector.hasEnemyFiredABullet(event), equalTo(false));
    }
    
    @Test
    public void testHasEnemyFiredABulletReturnsFalseWhenEnergyDropIsZeroZero1() {
        ScannedRobotEvent event = ScanEventUtil.createScannedRobotEventWithEnergy(10);
        bulletFiredDetector.hasEnemyFiredABullet(event);
        event = ScanEventUtil.createScannedRobotEventWithEnergy(9.99);
        assertThat(bulletFiredDetector.hasEnemyFiredABullet(event), equalTo(false));
    }
}
