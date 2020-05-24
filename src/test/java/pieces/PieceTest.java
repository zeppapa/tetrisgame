package pieces;

import javafx.scene.paint.Color;
import model.Map;
import model.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    Map testMap;
    Piece testPiece;
    Piece testPiece2;

    @BeforeEach
    void setUp() {
        testMap = new Map();
        testPiece = testMap.newPiece();
        testPiece2 = testPiece;
    }

    @Test
    void testNewPiece() {
        assertEquals(testPiece.centerX, 5);
        assertEquals(testPiece.centerY, 1);
        if (testPiece.name == 'i') {
            assertEquals(testPiece.aX, 4);
            assertEquals(testPiece.aY, 1);
            assertEquals(testPiece.bX, 6);
            assertEquals(testPiece.bY, 1);
            assertEquals(testPiece.cX, 7);
            assertEquals(testPiece.cY, 1);
            assertEquals(testPiece.color, Color.CYAN);
        }
        if (testPiece.name == 'o'){
            assertEquals(testPiece.aX, 5);
            assertEquals(testPiece.aY, 0);
            assertEquals(testPiece.bX, 6);
            assertEquals(testPiece.bY, 1);
            assertEquals(testPiece.cX, 6);
            assertEquals(testPiece.cY, 0);
            assertEquals(testPiece.color, Color.GOLD);
        }
        if (testPiece.name == 't'){
            assertEquals(testPiece.aX, 4);
            assertEquals(testPiece.aY, 1);
            assertEquals(testPiece.bX, 5);
            assertEquals(testPiece.bY, 0);
            assertEquals(testPiece.cX, 6);
            assertEquals(testPiece.cY, 1);
            assertEquals(testPiece.color, Color.PURPLE);
        }
        if (testPiece.name == 's'){
            assertEquals(testPiece.aX, 4);
            assertEquals(testPiece.aY, 1);
            assertEquals(testPiece.bX, 5);
            assertEquals(testPiece.bY, 0);
            assertEquals(testPiece.cX, 6);
            assertEquals(testPiece.cY, 0);
            assertEquals(testPiece.color, Color.GREEN);
        }
        if (testPiece.name == 'z'){
            assertEquals(testPiece.aX, 4);
            assertEquals(testPiece.aY, 0);
            assertEquals(testPiece.bX, 5);
            assertEquals(testPiece.bY, 0);
            assertEquals(testPiece.cX, 6);
            assertEquals(testPiece.cY, 1);
            assertEquals(testPiece.color, Color.RED);
        }
        if (testPiece.name == 'j'){
            assertEquals(testPiece.aX, 4);
            assertEquals(testPiece.aY, 0);
            assertEquals(testPiece.bX, 4);
            assertEquals(testPiece.bY, 1);
            assertEquals(testPiece.cX, 6);
            assertEquals(testPiece.cY, 1);
            assertEquals(testPiece.color, Color.BLUE);
        }
        if (testPiece.name == 'l'){
            assertEquals(testPiece.aX, 4);
            assertEquals(testPiece.aY, 1);
            assertEquals(testPiece.bX, 6);
            assertEquals(testPiece.bY, 1);
            assertEquals(testPiece.cX, 6);
            assertEquals(testPiece.cY, 0);
            assertEquals(testPiece.color, Color.ORANGE);
        }
    }

    @Test
    void testPieceFall(){
        testPiece2.fall();
        assertEquals(testPiece.centerY + 1, testPiece2.centerY);
        assertEquals(testPiece.aY + 1, testPiece2.aY);
        assertEquals(testPiece.bY + 1, testPiece2.bY);
        assertEquals(testPiece.cY + 1, testPiece2.cY);
    }

    @Test
    void testMovePieceLeft(){
        testPiece2.moveLeft();
        assertEquals(testPiece.centerX - 1, testPiece2.centerX);
        assertEquals(testPiece.aX - 1, testPiece2.aX);
        assertEquals(testPiece.bX - 1, testPiece2.bX);
        assertEquals(testPiece.cX - 1, testPiece2.cX);
    }

    @Test
    void testMovePieceRight(){
        testPiece2.moveLeft();
        assertEquals(testPiece.centerX + 1, testPiece2.centerX);
        assertEquals(testPiece.aX + 1, testPiece2.aX);
        assertEquals(testPiece.bX + 1, testPiece2.bX);
        assertEquals(testPiece.cX + 1, testPiece2.cX);
    }
}
