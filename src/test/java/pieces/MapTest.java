package pieces;

import javafx.scene.paint.Color;
import model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    Map testMap = new Map();
    @BeforeEach
    void setUp() {
        testMap.initMap();
    }

    @Test
    void testInitMap(){
        testMap.initMap();
        assertEquals(testMap.map[6][2], 0);
        assertEquals(testMap.rectangles[1][2].getFill(), Color.GRAY);
    }

    @Test
    void testShiftRowsDown(){
        testMap.map[4][5] = 3;
        testMap.clearRow(7);
        testMap.shiftRowsDown();
        assertEquals(testMap.map[5][5], 3);
    }

    @Test
    void testClearRow(){
        testMap.clearRow(8);
        assertEquals(testMap.map[8][5], 8);
    }
}
