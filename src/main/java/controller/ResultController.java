package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.tinylog.Logger;
import results.TetrisGameResults;
import results.TetrisGameResultsDao;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class ResultController {

    @FXML
    private TableView<TetrisGameResults> toptenTable;

    @FXML
    private TableColumn<TetrisGameResults, String> player;

    @FXML
    private TableColumn<TetrisGameResults, Integer> score;

    @FXML
    private TableColumn<TetrisGameResults, Integer> level;

    @FXML
    private TableColumn<TetrisGameResults, ZonedDateTime> created;

    private TetrisGameResultsDao tetrisGameResultsDao;

    public void back(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("Loading launch scene.");
    }


    @FXML
    public void initialize() {
        tetrisGameResultsDao = TetrisGameResultsDao.getInstance();

        List<TetrisGameResults> toptenList = tetrisGameResultsDao.findBest(10);

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));
        created.setCellValueFactory(new PropertyValueFactory<>("created"));


        created.setCellFactory(column -> {
            TableCell<TetrisGameResults, ZonedDateTime> cell = new TableCell<TetrisGameResults, ZonedDateTime>() {
                private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss Z");

                @Override
                protected void updateItem(ZonedDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(item.format(formatter));
                    }
                }
            };

            return cell;
        });

        ObservableList<TetrisGameResults> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(toptenList);

        toptenTable.setItems(observableResult);
    }

}
