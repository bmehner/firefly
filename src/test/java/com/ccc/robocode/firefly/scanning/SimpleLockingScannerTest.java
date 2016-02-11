package com.ccc.robocode.firefly.scanning;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ccc.robocode.firefly.Firefly;
import com.ccc.robocode.firefly.testutils.ScanEventUtil;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

@RunWith(MockitoJUnitRunner.class)
public class SimpleLockingScannerTest {
    
    @Mock
    private Firefly robotMock; 
    @InjectMocks
    private SimpleLockingScanner instance;
    
    @Test
    public void testMainScan() {
        instance.scan();
        verify(robotMock, times(1)).turnRadarRightRadians(anyDouble());
        verifyNoMoreInteractions(robotMock);
    }
    
    @Test
    public void testReactionOnScanEventRadarAlreadyOnTarget() {
        ScannedRobotEvent scanEvent = ScanEventUtil.createScannedRobotEventWithBearing(0);
        instance.scan(scanEvent);
        verify(robotMock, times(1)).getRadarTurnRemainingRadians();
        verify(robotMock, times(1)).setTurnRadarLeftRadians(0);
        verifyNoMoreInteractions(robotMock);
    }
    
    @Test
    public void testReactionOnScanEventRadarRemainingTurn() {
        ScannedRobotEvent scanEvent = ScanEventUtil.createScannedRobotEventWithBearing(0);
        when(robotMock.getRadarTurnRemainingRadians()).thenReturn(Math.PI);
        instance.scan(scanEvent);
        verify(robotMock, times(1)).getRadarTurnRemainingRadians();
        verify(robotMock, times(1)).setTurnRadarLeftRadians(Math.PI);
        verifyNoMoreInteractions(robotMock);
    }
    
}
