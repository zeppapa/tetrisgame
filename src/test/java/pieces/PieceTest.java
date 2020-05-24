package pieces;

import javafx.scene.paint.Color;
import model.Map;
import model.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    Map testMap = new Map();
    Piece testPiece = new Piece('i');
    Piece testFallenPiece = new Piece('i');
    Piece testRightMovedPiece = new Piece('i');
    Piece testLeftMovedPiece = new Piece('i');
    Piece testChangedFormedPiece = new Piece('i');

    @BeforeEach
    void setUp() {
        testFallenPiece.fall();
        testRightMovedPiece.moveRight();
        testLeftMovedPiece.moveLeft();
        testChangedFormedPiece.changeForm(testMap);
    }

    @Test
    void testNewPiece() {
        assertEquals(testPiece.centerX, 5);
        assertEquals(testPiece.centerY, 1);
        assertEquals(testPiece.aX, 4);
        assertEquals(testPiece.aY, 1);
        assertEquals(testPiece.bX, 6);
        assertEquals(testPiece.bY, 1);
        assertEquals(testPiece.cX, 7);
        assertEquals(testPiece.cY, 1);
        assertEquals(testPiece.color, Color.CYAN);
        assertEquals(testPiece.lowest, 1);
        assertEquals(testPiece.leftMost, 4);
        assertEquals(testPiece.rightMost, 7);
        assertEquals(testPiece.top, 1);
    }


    @Test
    void testPieceFall(){
        assertEquals(testPiece.centerY + 1, testFallenPiece.centerY);
        assertEquals(testPiece.aY + 1, testFallenPiece.aY);
        assertEquals(testPiece.bY + 1, testFallenPiece.bY);
        assertEquals(testPiece.cY + 1, testFallenPiece.cY);
    }

    @Test
    void testMovePieceLeft(){
        assertEquals(testPiece.centerX - 1, testLeftMovedPiece.centerX);
        assertEquals(testPiece.aX - 1, testLeftMovedPiece.aX);
        assertEquals(testPiece.bX - 1, testLeftMovedPiece.bX);
        assertEquals(testPiece.cX - 1, testLeftMovedPiece.cX);
    }

    @Test
    void testMovePieceRight(){
        assertEquals(testPiece.centerX + 1, testRightMovedPiece.centerX);
        assertEquals(testPiece.aX + 1, testRightMovedPiece.aX);
        assertEquals(testPiece.bX + 1, testRightMovedPiece.bX);
        assertEquals(testPiece.cX + 1, testRightMovedPiece.cX);
    }

    @Test
    void testPieceChangeForm(){
        assertEquals(testPiece.centerX + 1, testChangedFormedPiece.centerX);
        assertEquals(testPiece.centerY, testChangedFormedPiece.centerY);
        assertEquals(testPiece.aX + 2, testChangedFormedPiece.aX);
        assertEquals(testPiece.aY - 1, testChangedFormedPiece.aY);
        assertEquals(testPiece.bX, testChangedFormedPiece.bX);
        assertEquals(testPiece.bY + 1, testChangedFormedPiece.bY);
        assertEquals(testPiece.cX - 1, testChangedFormedPiece.cX);
        assertEquals(testPiece.cY + 2, testChangedFormedPiece.cY);

    }
}
