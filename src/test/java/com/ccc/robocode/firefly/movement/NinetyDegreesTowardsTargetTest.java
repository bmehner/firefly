package com.ccc.robocode.firefly.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ccc.robocode.firefly.Firefly;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

@RunWith(MockitoJUnitRunner.class)
public class NinetyDegreesTowardsTargetTest {
    
    private static final String TEST_NAME_ROBOT = "TestRobot";
    private static final double TEST_ENERGY_ROBOT = 1;
    private static final double TEST_BEARING_ROBOT_IN_RADIANS = 0;
    private static final double TEST_DISTANCE_ROBOT_IN_PIXELS = 100;
    private static final double TEST_HEADING_ROBOT = 1;
    private static final double TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN = 0;
    
    @Mock
    private Firefly robotMock; 
    @Mock
    private Random randomMock;
    @InjectMocks
    private NinetyDegreesTowardsTarget instance;
    
    @Test
    public void testMoveTargetInFront() {
        //given
        ScannedRobotEvent event = createScannedRobotEvent(TEST_BEARING_ROBOT_IN_RADIANS);
        //when
        when(robotMock.getHeadingRadians()).thenReturn(0.0);
        instance.move(event);
        //then
        verify(robotMock, times(2)).getHeadingRadians();
        verify(robotMock, times(1)).setTurnRightRadians(Math.PI/2);
        verifyNoMoreInteractions(robotMock, randomMock);
    }
    
    @Test
    public void testMoveTarget90DegreesToTheRight() {
        //given
        ScannedRobotEvent event = createScannedRobotEvent(Math.PI/2);
        //when
        when(robotMock.getHeadingRadians()).thenReturn(0.0);
        instance.move(event);
        //then
        verify(robotMock, times(2)).getHeadingRadians();
        verify(robotMock, times(1)).setTurnRightRadians(-Math.PI);
        verifyNoMoreInteractions(robotMock, randomMock);
    }
    
    @Test
    public void testMoveTarget90DegreesToTheLeft() {
        //given
        ScannedRobotEvent event = createScannedRobotEvent(-Math.PI/2);
        //when
        when(robotMock.getHeadingRadians()).thenReturn(0.0);
        instance.move(event);
        //then
        verify(robotMock, times(2)).getHeadingRadians();
        verify(robotMock, times(1)).setTurnRightRadians(0);
        verifyNoMoreInteractions(robotMock, randomMock);
    }
    
    @Test
    public void testMoveTargetAt180Degrees() {
        //given
        ScannedRobotEvent event = createScannedRobotEvent(Math.PI);
        //when
        when(robotMock.getHeadingRadians()).thenReturn(0.0);
        instance.move(event);
        //then
        verify(robotMock, times(2)).getHeadingRadians();
        verify(robotMock, times(1)).setTurnRightRadians(-Math.PI/2);
        verifyNoMoreInteractions(robotMock, randomMock);
    }

    @Test
    public void testEscapeWallHittingItMovingForward() {
        //given
        double bearingHitInFrontInRadians = 1; 
        HitWallEvent event = new HitWallEvent(bearingHitInFrontInRadians);
        instance.escapeWall(event);
        //then
        verify(robotMock, times(1)).setBack(anyDouble());
        verify(robotMock, never()).setAhead(anyDouble());
        verifyNoMoreInteractions(robotMock, randomMock);
    }
    
    @Test
    public void testEscapeWallHittingItMovingBackward() {
        //given
        double bearingHitInBackInRadians = 3; 
        HitWallEvent event = new HitWallEvent(bearingHitInBackInRadians);
        //when 
        instance.escapeWall(event);
        //then
        verify(robotMock, never()).setBack(anyDouble());
        verify(robotMock, times(1)).setAhead(anyDouble());
        verifyNoMoreInteractions(robotMock, randomMock);
    }

    @Test
    public void testDodgeBulletAhead() {
        ScannedRobotEvent event = createScannedRobotEvent(TEST_BEARING_ROBOT_IN_RADIANS);
        // when
        when(randomMock.nextBoolean()).thenReturn(Boolean.TRUE);
        when(randomMock.nextDouble()).thenReturn(0.5);
        instance.dodgeBullet(event);
        // then
        verify(robotMock).setAhead(0.5 * 200);
    }

    @Test
    public void testDodgeBulletBack() {
        ScannedRobotEvent event = createScannedRobotEvent(TEST_BEARING_ROBOT_IN_RADIANS);
        // when
        when(randomMock.nextBoolean()).thenReturn(Boolean.FALSE);
        when(randomMock.nextDouble()).thenReturn(0.5);
        instance.dodgeBullet(event);
        // then
        verify(robotMock).setBack(0.5 * 200);
    }
    
    @Test
    public void testDodgeBulletDistanceTooSmall() {
        // given
        ScannedRobotEvent event = createScannedRobotEvent(TEST_BEARING_ROBOT_IN_RADIANS);
        // when
        when(randomMock.nextBoolean()).thenReturn(Boolean.FALSE);
        when(randomMock.nextDouble()).thenReturn(0.1);
        instance.dodgeBullet(event);
        // then
        verify(robotMock).setBack(100);
    }
    
    private ScannedRobotEvent createScannedRobotEvent(double bearingInRadians) {
        // given
        ScannedRobotEvent event = new ScannedRobotEvent(TEST_NAME_ROBOT, 
                TEST_ENERGY_ROBOT, bearingInRadians, 
                TEST_DISTANCE_ROBOT_IN_PIXELS, TEST_HEADING_ROBOT, 
                TEST_HEADING_VELOCITY_IN_PIXELS_PER_TURN, false);
        return event;
    }
}
