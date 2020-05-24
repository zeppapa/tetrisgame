package model;

import javafx.scene.paint.Color;

/**
 * Class representing the Tetronimo piece.
 */

public class Piece {
    /**
     * Representing the coordinates, value, name, color, form, and border coordinates of the piece.
     */
    public int centerX = 5;
    public int centerY = 1;
    public int aX;
    public int aY;
    public int bX;
    public int bY;
    public int cX;
    public int cY;
    public int value = 1;
    public char name;
    public Color color;
    int form = 1;
    public int leftMost;
    public int rightMost;
    public int lowest;
    public int top;

    /**
     * Empty constructor of the {@code Piece}.
     */

    public Piece(){}

    /**
     * Creating an existing piece by its name.
     * The rectangle named "center" ({@code centerX}, {@code centerY}) is defined by its coordinates, the other
     * rectangles are counted by their position compared to the "center".
     * Sets the border coordinates of the piece.
     * @param name decides where to set the other coordinates and the color of the piece.
     */

    public Piece(char name){
        switch (name) {
            case 'i' :
                color = Color.CYAN;
                aX = centerX - 1;
                aY = centerY;
                bX = centerX + 1;
                bY = centerY;
                cX = centerX + 2;
                cY = centerY;
                this.name = name;
                break;
            case 'o' :
                color = Color.GOLD;
                aX = centerX;
                aY = centerY - 1;
                bX = centerX + 1;
                bY = centerY;
                cX = centerX + 1;
                cY = centerY - 1;
                this.name = name;
                break;
            case 't' :
                color = Color.PURPLE;
                aX = centerX - 1;
                aY = centerY;
                bX = centerX;
                bY = centerY - 1;
                cX = centerX + 1;
                cY = centerY;
                this.name = name;
                break;
            case 's' :
                color = Color.GREEN;
                aX = centerX - 1;
                aY = centerY;
                bX = centerX;
                bY = centerY - 1;
                cX = centerX + 1;
                cY = centerY - 1;
                this.name = name;
                break;
            case 'z' :
                color = Color.RED;
                aX = centerX - 1;
                aY = centerY - 1;
                bX = centerX;
                bY = centerY - 1;
                cX = centerX + 1;
                cY = centerY;
                this.name = name;
                break;
            case 'j' :
                color = Color.BLUE;
                aX = centerX - 1;
                aY = centerY - 1;
                bX = centerX - 1;
                bY = centerY;
                cX = centerX + 1;
                cY = centerY;
                this.name = name;
                break;
            case 'l' :
                color = Color.ORANGE;
                aX = centerX - 1;
                aY = centerY;
                bX = centerX + 1;
                bY = centerY;
                cX = centerX + 1;
                cY = centerY - 1;
                this.name = name;
                break;
        }
        setMosts();
    }

    /**
     * Sets the border coordinates of the piece.
     */

    private void setMosts(){
        leftMost = centerX;
        if(aX < leftMost) leftMost = aX;
        if(bX < leftMost) leftMost = bX;
        if(cX < leftMost) leftMost = cX;
        rightMost = centerX;
        if(aX > rightMost) rightMost = aX;
        if(bX > rightMost) rightMost = bX;
        if(cX > rightMost) rightMost = cX;
        lowest = centerY;
        if(aY > lowest) lowest = aY;
        if(bY > lowest) lowest = bY;
        if(cY > lowest) lowest = cY;
        top = centerY;
        if (aY < top) top = aY;
        if (bY < top) top = bY;
        if (cY < top) top = cY;

    }

    /**
     * Makes a piece fall by setting its {@code y} coordinates
     * and after that sets the border coordinates of the piece.
     */

    public void fall(){
        centerY += 1;
        aY += 1;
        bY += 1;
        cY += 1;
        setMosts();
    }

    /**
     * Makes a piece move right by setting its {@code x} coordinates
     * and after that sets the border coordinates of the piece.
     */

    public void moveRight(){
        centerX += 1;
        aX += 1;
        bX += 1;
        cX += 1;
        setMosts();
    }

    /**
     * Makes a piece move left by setting its {@code x} coordinates
     * and after that sets the border coordinates of the piece.
     */

    public void moveLeft(){
        centerX -= 1;
        aX -= 1;
        bX -= 1;
        cX -= 1;
        setMosts();
    }

    /**
     * Turn the piece around if possible, otherwise trying to move to the possible direction where it can be.
     * If the piece cannot be turned, it stays in its original position.
     * @param map if the map where the piece is.
     */

    public void changeForm(Map map){
        int tempCenterX = centerX, tempCenterY = centerY, tempAX = aX, tempAY = aY, tempBX = bX, tempBY = bY, tempCX = cX, tempCY = cY, tempForm = form;
        switch (name){
            case 'i':
                if (form == 1){
                    centerX += 1;
                    aX += 2;
                    aY -= 1;
                    bY += 1;
                    cX -= 1;
                    cY += 2;
                    form = 2;
                    break;
                }
                if (form == 2){
                    centerY += 1;
                    aX += 1;
                    aY += 2;
                    bX -= 1;
                    cX -= 2;
                    cY -= 1;
                    form = 3;
                    break;
                }
                if (form == 3){
                    centerX -= 1;
                    aX -= 2;
                    aY += 1;
                    bY -= 1;
                    cX += 1;
                    cY -= 2;
                    form = 4;
                    break;
                }
                if (form == 4){
                    centerY -= 1;
                    aX -= 1;
                    aY -= 2;
                    bX += 1;
                    cX += 2;
                    cY += 1;
                    form = 1;
                    break;
                }
            case 't':
                if (form == 1){
                    aX += 1;
                    aY -= 1;
                    bX += 1;
                    bY += 1;
                    cX -= 1;
                    cY += 1;
                    form = 2;
                    break;
                }
                if (form == 2){
                    aX += 1;
                    aY += 1;
                    bX -= 1;
                    bY += 1;
                    cX -= 1;
                    cY -= 1;
                    form = 3;
                    break;
                }
                if (form == 3){
                    aX -= 1;
                    aY += 1;
                    bX -= 1;
                    bY -= 1;
                    cX += 1;
                    cY -= 1;
                    form = 4;
                    break;
                }
                if (form == 4){
                    aX -= 1;
                    aY -= 1;
                    bX += 1;
                    bY -= 1;
                    cX += 1;
                    cY += 1;
                    form = 1;
                    break;
                }
            case 's':
                if (form == 1){
                    aX += 1;
                    aY -= 1;
                    bX += 1;
                    bY += 1;
                    cY += 2;
                    form = 2;
                    break;
                }
                if (form == 2){
                    aX += 1;
                    aY += 1;
                    bX -= 1;
                    bY += 1;
                    cX -= 2;
                    form = 3;
                    break;
                }
                if (form == 3){
                    aX -= 1;
                    aY += 1;
                    bX -= 1;
                    bY -= 1;
                    cY -= 2;
                    form = 4;
                    break;
                }
                if (form == 4){
                    aX -= 1;
                    aY -= 1;
                    bX += 1;
                    bY -= 1;
                    cX += 2;
                    form = 1;
                    break;
                }
            case 'z':
                if (form == 1){
                    aX += 2;
                    bX += 1;
                    bY += 1;
                    cX -= 1;
                    cY += 1;
                    form = 2;
                    break;
                }
                if (form == 2){
                    aY += 2;
                    bX -= 1;
                    bY += 1;
                    cX -= 1;
                    cY -= 1;
                    form = 3;
                    break;
                }
                if (form == 3){
                    aX -= 2;
                    bX -= 1;
                    bY -= 1;
                    cX += 1;
                    cY -= 1;
                    form = 4;
                    break;
                }
                if (form == 4){
                    aY -= 2;
                    bX += 1;
                    bY -= 1;
                    cX += 1;
                    cY += 1;
                    form = 1;
                    break;
                }
            case 'j':
                if (form == 1){
                    aX += 2;
                    bX += 1;
                    bY -= 1;
                    cX -= 1;
                    cY += 1;
                    form = 2;
                    break;
                }
                if (form == 2){
                    aY += 2;
                    bX += 1;
                    bY += 1;
                    cX -= 1;
                    cY -= 1;
                    form = 3;
                    break;
                }
                if (form == 3){
                    aX -= 2;
                    bX -= 1;
                    bY += 1;
                    cX += 1;
                    cY -= 1;
                    form = 4;
                    break;
                }
                if (form == 4){
                    aY -= 2;
                    bX -= 1;
                    bY -= 1;
                    cX += 1;
                    cY += 1;
                    form = 1;
                    break;
                }
            case 'l':
                if (form == 1){
                    aX += 1;
                    aY -= 1;
                    bX -= 1;
                    bY += 1;
                    cY += 2;
                    form = 2;
                    break;
                }
                if (form == 2){
                    aX += 1;
                    aY += 1;
                    bX -= 1;
                    bY -= 1;
                    cX -= 2;
                    form = 3;
                    break;
                }
                if (form == 3){
                    aX -= 1;
                    aY += 1;
                    bX += 1;
                    bY -= 1;
                    cY -= 2;
                    form = 4;
                    break;
                }
                if (form == 4){
                    aX -= 1;
                    aY -= 1;
                    bX += 1;
                    bY += 1;
                    cX += 2;
                    form = 1;
                    break;
                }
        }
        setMosts();
        int changedCenterX = centerX, changedCenterY = centerY, changedAX = aX, changedAY = aY, changedBX = bX, changedBY = bY, changedCX = cX, changedCY = cY;
        if (leftMost <= 0){
            centerX += 1 - leftMost; aX += 1 - leftMost; bX += 1 - leftMost; cX += 1 - leftMost;
        }
        if (rightMost >= 10){
            centerX -= rightMost - 10; aX -= rightMost - 10; bX -= rightMost - 10; cX -= rightMost - 10;
        }
        if (centerY > 22 || aY > 22 || bY > 22 || cY > 22){
            centerX = tempCenterX; centerY = tempCenterY; aX = tempAX; aY = tempAY; bX = tempBX; bY = tempBY; cX = tempCX; cY = tempCY;
            form = tempForm;
        }
        if (map.map[centerY][centerX] == 9 || map.map[aY][aX] == 9 || map.map[bY][bX] == 9 || map.map[cY][cX] == 9){
            if (leftMost > 1) { centerX = changedCenterX - 1; aX = changedAX - 1; bX = changedBX - 1; cX = changedCX - 1; }
            if (map.map[centerY][centerX] == 9 || map.map[aY][aX] == 9 || map.map[bY][bX] == 9 || map.map[cY][cX] == 9){
                if (rightMost < 10){ centerX = changedCenterX + 1; aX = changedAX + 1; bX = changedBX + 1; cX = changedCX + 1;}
                if (map.map[centerY][centerX] == 9 || map.map[aY][aX] == 9 || map.map[bY][bX] == 9 || map.map[cY][cX] == 9){
                    if (top > 2 && leftMost >= 1 && rightMost <= 10) { centerX = changedCenterX; aX = changedAX; bX = changedBX; cX = changedCX;
                        centerY = changedCenterY - 1; aY = changedAY - 1; bY = changedBY - 1; cY = changedCY - 1; }
                    if (map.map[centerY][centerX] == 9 || map.map[aY][aX] == 9 || map.map[bY][bX] == 9 || map.map[cY][cX] == 9){
                        if (top > 2 && leftMost > 1) {centerX = changedCenterX - 1; aX = changedAX - 1; bX = changedBX - 1; cX = changedCX - 1;
                            centerY = changedCenterY - 1; aY = changedAY - 1; bY = changedBY - 1; cY = changedCY - 1; }
                        if (map.map[centerY][centerX] == 9 || map.map[aY][aX] == 9 || map.map[bY][bX] == 9 || map.map[cY][cX] == 9){
                            if (top > 2 && rightMost < 10) { centerX = changedCenterX + 1; aX = changedAX + 1; bX = changedBX + 1; cX = changedCX + 1;
                                centerY = changedCenterY - 1; aY = changedAY - 1; bY = changedBY - 1; cY = changedCY - 1; }
                            if (map.map[centerY][centerX] == 9 || map.map[aY][aX] == 9 || map.map[bY][bX] == 9 || map.map[cY][cX] == 9){
                                centerX = tempCenterX; centerY = tempCenterY; aX = tempAX; aY = tempAY; bX = tempBX; bY = tempBY; cX = tempCX; cY = tempCY;
                                form = tempForm;
                            }
                        }
                    }
                }
            }
        }
        setMosts();
    }
}
