package controller;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Map;
import model.Piece;
import org.tinylog.Logger;
import results.TetrisGameResults;
import results.TetrisGameResultsDao;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;


public class GameController {
    private IntegerProperty score = new SimpleIntegerProperty();
    private IntegerProperty level = new SimpleIntegerProperty();
    private int rowsForLevel = 0;
    private int period = 500;
    private boolean isOver;
    private boolean isStarted = false;
    private String userName;
    private int finalScore;

    private TetrisGameResultsDao tetrisGameResultsDao;

    @FXML
    private Pane gamePane;

    @FXML
    private Button startButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label overLabel;

    @FXML
    private Label levelLabel;

    @FXML
    private Button finishButton;

    @FXML
    private Label userNameLabel;


    Map map = new Map();
    Piece piece = new Piece();

    public void initdata(String userName) {
        this.userName = userName;
        userNameLabel.setText("Current user: " + this.userName);
    }

    private void initGameState(){
        for (int i = 0; i <= 22; i++){
            for (int j = 0; j <= 11; j++){
                gamePane.getChildren().add(map.rectangles[i][j]);
            }
        }
    }

    private void drawGameState(){
        for (int i = 2; i < 22; i++){
            for (int j = 1; j < 11; j++){
                if (map.map[i][j] == 1){
                    map.rectangles[i][j].setFill(piece.color);
                } else if (map.map[i][j] == 0){
                    map.rectangles[i][j].setFill(Color.WHITE);
                }
            }
        }
    }

    @FXML
    private void startGame() {
        if (!isStarted) {
            Logger.info("Starting a new game");
            addNewPiece();
            moveOnKeyPress();
            startTimer();
            isStarted = true;
        }
        if (isOver){
            initialize();
        }
    }

    private void startTimer(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pieceFall();
                        if (!canThePieceFall() && piece.lowest == 1){
                            isOver = true;
                            overLabel.setText("Game over!");
                            startButton.setText("Reset");
                            Logger.info("The game is over");
                            Logger.info("The player finished the game with {} score", score.get());
                            timer.cancel();
                            timer.purge();
                        }
                        if (rowsForLevel >= 10) {
                            level.set(level.get() + 1);
                            Logger.info("Level is increasing");
                            rowsForLevel -= 10;
                            period *= 0.9;
                            timer.cancel();
                            startTimer();
                        }
                    }
                });
            }
        };
        timer.schedule(task,0, period);
    }

    private void moveOnKeyPress(){
        gamePane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case D:
                        if (canThePieceMoveRight()) {
                            map.setValues(piece, 0);
                            piece.moveRight();
                            map.setValues(piece);
                            drawGameState();
                        }
                        break;
                    case A:
                        if (canThePieceMoveLeft()) {
                            map.setValues(piece, 0);
                            piece.moveLeft();
                            map.setValues(piece);
                            drawGameState();
                        }
                        break;
                    case S:
                        pieceFall();
                        score.set(score.get() + 1);
                        break;
                    case W:
                        map.setValues(piece, 0);
                        piece.changeForm(map);
                        map.setValues(piece);
                        drawGameState();
                        break;
                }
            }
        });
    }

    @FXML
    private void initialize() {
        tetrisGameResultsDao = TetrisGameResultsDao.getInstance();
        map.initMap();
        initGameState();
        score.set(0);
        level.set(0);
        scoreLabel.textProperty().bind(score.asString());
        levelLabel.textProperty().bind(level.asString());
        isStarted = false;
        isOver = false;
        overLabel.setText("");
        startButton.setText("Start");
        Logger.info("Initializing the map");
    }

    public void addNewPiece() {
        piece = map.newPiece();
        drawGameState();
    }

    public void pieceFall() {
        if (canThePieceFall()) {
            map.setValues(piece, 0);
            piece.fall();
            map.setValues(piece);
            drawGameState();
        } else {
            map.setValues(piece, 9);
            int numberOfClearedRows = 0;
            if (map.isThisRowFull(piece.centerY)){
                map.clearRow(piece.centerY);
                numberOfClearedRows +=1;
            }
            if (map.isThisRowFull(piece.aY)){
                map.clearRow(piece.aY);
                numberOfClearedRows +=1;
            }
            if (map.isThisRowFull(piece.bY)){
                map.clearRow(piece.bY);
                numberOfClearedRows +=1;
            }
            if (map.isThisRowFull(piece.cY)){
                map.clearRow(piece.cY);
                numberOfClearedRows +=1;
            }
            map.shiftRowsDown();
            if (numberOfClearedRows == 1)
                score.set(score.get() + 40 * (level.get() + 1));
            if (numberOfClearedRows == 2)
                score.set(score.get() + 100 * (level.get() + 1));
            if (numberOfClearedRows == 3)
                score.set(score.get() + 300 * (level.get() + 1));
            if (numberOfClearedRows == 4)
                score.set(score.get() + 1200 * (level.get() + 1));
            finalScore += score.get();
            rowsForLevel += numberOfClearedRows;

            addNewPiece();
        }
    }

    private boolean canThePieceFall(){
        if (piece.lowest == 21 || map.map[piece.centerY + 1][piece.centerX] == 9 || map.map[piece.aY + 1][piece.aX] == 9 ||
                map.map[piece.bY + 1][piece.bX] == 9 || map.map[piece.cY + 1][piece.cX] == 9){
            return false;
        } else return true;
    }

    private boolean canThePieceMoveRight(){
        if (piece.rightMost == 10 || map.map[piece.centerY][piece.centerX + 1] == 9 || map.map[piece.aY][piece.aX + 1] == 9 ||
                map.map[piece.bY][piece.bX + 1] == 9 || map.map[piece.cY][piece.cX + 1] == 9){
            return false;
        } else return true;
    }

    private boolean canThePieceMoveLeft(){
        if (piece.leftMost == 1 || map.map[piece.centerY][piece.centerX - 1] == 9 || map.map[piece.aY][piece.aX - 1] == 9 ||
                map.map[piece.bY][piece.bX - 1] == 9 || map.map[piece.cY][piece.cX - 1] == 9){
            return false;
        } else return true;
    }

    private TetrisGameResults getResult() {

        TetrisGameResults result = TetrisGameResults.builder()
                .player(userName)
                .score(score.get())
                .level(level.get())
                .build();
        return result;
    }


    public void finishGame(ActionEvent actionEvent) throws IOException {
        if (isOver){
            tetrisGameResultsDao.persist(getResult());
        }
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/results.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("Finished game, loading Top Ten scene.");
    }
}
