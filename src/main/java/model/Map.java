package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class representing the field of the game
 */

public class Map {
    /**
     * The array defining the game field with the border and the invisible invisible line above the upper border
     */
    public int[][] map = new int[23][12];

    /**
     * The array defining the {@code Rectangle} class rectangles
     */

    public Rectangle[][] rectangles = new Rectangle[23][12];

    /**
     * The array defines an ordered row contains the {@code Piece} object names
     */

    public char[] pieceRow = new char[7];

    /**
     * The array defines which piece is used from the {@code pieceRow} array
     */

    boolean[] pieceIsUsed = new boolean[7];

    /**
     * Creates a new {@code Map} object
     */

    public Map(){
    }

    /**
     * Initialize the attributes of the {@code Map} object
     * Filling the {@code map} array and the {@code rectangles} array.
     * {@code 0} means the field is empty
     * The border rectangles gets a gray fill with a black stroke.
     * Initializing the {@code pieceRow} array and the {@code pieceIsUsed} array.
     */

    public void initMap(){
        for (int i = 0; i < 23; i++){
            for (int j = 0; j < 12; j++){
                map[i][j] = 0;
            }
        }
        for (int i = 0; i <= 22; i++){
            for (int j = 0; j <= 11; j++){
                Rectangle rectangle = new Rectangle(j * 20, i * 20, 20, 20);
                if (i == 1 || i == 22 || (j == 0 && i > 0) || (j == 11 && i > 0)){
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(0.5);
                    rectangle.setFill(Color.GRAY);
                } else {
                    rectangle.setFill(Color.WHITE);
                    rectangle.setStrokeWidth(0.5);
                    rectangle.setStroke(Color.WHITE);
                }
                rectangles[i][j] = rectangle;
            }
        }
        pieceRow[0] = 'i'; pieceRow[1] = 'o'; pieceRow[2] = 't'; pieceRow[3] = 's'; pieceRow[4] = 'z'; pieceRow[5] = 'j'; pieceRow[6] = 'l';
        pieceIsUsed[0] = false; pieceIsUsed[1] = false; pieceIsUsed[2] = false; pieceIsUsed[3] = false; pieceIsUsed[4] = false; pieceIsUsed[5] = false;
        pieceIsUsed[6] = false;
    }

    /**
     * Sets the values of the map in the coordinates of the piece with the piece's own value.
     * @param piece is the current {@code Piece} what gives the coordinates.
     */

    public void setValues(Piece piece){
        map[piece.centerY][piece.centerX] = piece.value;
        map[piece.aY][piece.aX] = piece.value;
        map[piece.bY][piece.bX] = piece.value;
        map[piece.cY][piece.cX] = piece.value;
    }

    /**
     * Sets the values of the map in the coordinates of the piece with the given number.
     * @param piece is the current {@code Piece} what gives the coordinates.
     * @param value is the new value in the piece's coordinates.
     */

    public void setValues(Piece piece, int value){
        map[piece.centerY][piece.centerX] = value;
        map[piece.aY][piece.aX] = value;
        map[piece.bY][piece.bX] = value;
        map[piece.cY][piece.cX] = value;
    }

    /**
     * Tests if the given row is full int the map.
     * @param i is the tested row.
     * @return {@code true} if the row is full, {@code false} otherwise.
     */

    public boolean isThisRowFull(int i){
        boolean foundAnEmptyInTheRow = false;

        for (int j = 1; j < 11; j++){
            if (map[i][j] == 0 || map[i][j] == 8){
                foundAnEmptyInTheRow = true;
                break;
            }
        }
        if (foundAnEmptyInTheRow){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Clears the selected row. {@code 8} means the field is became empty, but the table was not shifted down yet.
     * @param i is the cleared row of the map.
     */

    public void clearRow(int i){
        for (int j = 1; j < 11; j++){
            map[i][j] = 8;
        }
    }

    /**
     * When the map has cleared rows, this makes the above rows fall down.
     */

    public void shiftRowsDown(){
        for (int u = 22; u > 1; u--) {
            if (map[u][1] == 8) {
                for (int i = u; i > 1; i--) {
                    for (int j = 1; j < 11; j++) {
                        map[i][j] = map[i - 1][j];
                        rectangles[i][j].setFill(rectangles[i - 1][j].getFill());
                    }
                }
                u += 1;
            }
        }
    }

    /**
     * Adds a new {@code Piece} to the map.
     * @return the newly created {@code piece} object.
     */

    public Piece newPiece(){
        int random = (int) (Math.random() * 7);
        while (pieceIsUsed[random]){
            random = (int) (Math.random() * 7);
        }

        Piece piece = new Piece(pieceRow[random]);
        pieceIsUsed[random] = true;

        if (pieceIsUsed[0] && pieceIsUsed[1] && pieceIsUsed[2] &&
                pieceIsUsed[3] &&pieceIsUsed[4] && pieceIsUsed[5] &&pieceIsUsed[6]){
            for (int i = 0; i < 7; i++){
                pieceIsUsed[i] = false;
            }
        }

        map[piece.centerY][piece.centerX] = piece.value;
        map[piece.aY][piece.aX] = piece.value;
        map[piece.bY][piece.bX] = piece.value;
        map[piece.cY][piece.cX] = piece.value;
        return piece;
    }
}
