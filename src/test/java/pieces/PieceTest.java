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
        assertEquals(testPiece.getCenterX(), 5);
        assertEquals(testPiece.getCenterY(), 1);
        assertEquals(testPiece.getaX(), 4);
        assertEquals(testPiece.getaY(), 1);
        assertEquals(testPiece.getbX(), 6);
        assertEquals(testPiece.getbY(), 1);
        assertEquals(testPiece.getcX(), 7);
        assertEquals(testPiece.getcY(), 1);
        assertEquals(testPiece.getColor(), Color.CYAN);
        assertEquals(testPiece.getLowest(), 1);
        assertEquals(testPiece.getLeftMost(), 4);
        assertEquals(testPiece.getRightMost(), 7);
        assertEquals(testPiece.getTop(), 1);
    }


    @Test
    void testPieceFall(){
        assertEquals(testPiece.getCenterY() + 1, testFallenPiece.getCenterY());
        assertEquals(testPiece.getaY() + 1, testFallenPiece.getaY());
        assertEquals(testPiece.getbY() + 1, testFallenPiece.getbY());
        assertEquals(testPiece.getcY() + 1, testFallenPiece.getcY());
    }

    @Test
    void testMovePieceLeft(){
        assertEquals(testPiece.getCenterX() - 1, testLeftMovedPiece.getCenterX());
        assertEquals(testPiece.getaX() - 1, testLeftMovedPiece.getaX());
        assertEquals(testPiece.getbX() - 1, testLeftMovedPiece.getbX());
        assertEquals(testPiece.getcX() - 1, testLeftMovedPiece.getcX());
    }

    @Test
    void testMovePieceRight(){
        assertEquals(testPiece.getCenterX() + 1, testRightMovedPiece.getCenterX());
        assertEquals(testPiece.getaX() + 1, testRightMovedPiece.getaX());
        assertEquals(testPiece.getbX() + 1, testRightMovedPiece.getbX());
        assertEquals(testPiece.getcX() + 1, testRightMovedPiece.getcX());
    }

    @Test
    void testPieceChangeForm(){
        assertEquals(testPiece.getCenterX() + 1, testChangedFormedPiece.getCenterX());
        assertEquals(testPiece.getCenterY(), testChangedFormedPiece.getCenterY());
        assertEquals(testPiece.getaX() + 2, testChangedFormedPiece.getaX());
        assertEquals(testPiece.getaY() - 1, testChangedFormedPiece.getaY());
        assertEquals(testPiece.getbX(), testChangedFormedPiece.getbX());
        assertEquals(testPiece.getbY() + 1, testChangedFormedPiece.getbY());
        assertEquals(testPiece.getcX() - 1, testChangedFormedPiece.getcX());
        assertEquals(testPiece.getcY() + 2, testChangedFormedPiece.getcY());

    }
}
