package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Map {
    public int[][] map = new int[23][12];

    public Rectangle[][] rectangles = new Rectangle[23][12];

    public char[] pieceRow = new char[7];
    boolean[] pieceIsUsed = new boolean[7];


    public Map(){
    }

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

    public int[][] getMap(){
        return map;
    }

    public void setValues(Piece piece){
        map[piece.centerY][piece.centerX] = piece.value;
        map[piece.aY][piece.aX] = piece.value;
        map[piece.bY][piece.bX] = piece.value;
        map[piece.cY][piece.cX] = piece.value;
    }

    public void setValues(Piece piece, int value){
        map[piece.centerY][piece.centerX] = value;
        map[piece.aY][piece.aX] = value;
        map[piece.bY][piece.bX] = value;
        map[piece.cY][piece.cX] = value;
    }

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

    public void clearRow(int u){
        for (int j = 1; j < 11; j++){
            map[u][j] = 8;
        }
    }

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
