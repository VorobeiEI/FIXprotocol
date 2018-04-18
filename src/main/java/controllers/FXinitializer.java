package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import quickfix.FieldNotFound;
import quickfix.InvalidMessage;
import services.FileReaderService;
import services.FileWriterService;

import java.io.File;

public class FXinitializer {
    private Controller controller;

    private FileReaderService fileReaderService;

    private FileWriterService fileWriterService;

    private Stage primaryStage;

    private Alert alert;

    @FXML
    public TextField filename;
    @FXML
    public TextField reportFile;
    @FXML
    public Button start;
    @FXML
    public Button cancel;
    @FXML
    public Button browseLogFile;
    @FXML
    public Button browseReportFile;

    public FXinitializer() {
        primaryStage = new Stage();
    }

    @FXML
    public void initialize() {
        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.exit(0));

        start.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (filename.getText().isEmpty() || reportFile.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Вы не выбрали файлы для сохранения и чттения, пожалуйста выберите файлы");
                alert.setResizable(true);
                alert.showAndWait();
            } else {
                fileReaderService = new FileReaderService(filename.getText());

                fileWriterService = new FileWriterService(reportFile.getText());
                controller = new Controller(fileReaderService, fileWriterService);
                try {
                    controller.startManagedBook();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("Менеджмент книги завершен");
                    alert.setResizable(true);
                    alert.showAndWait();
                } catch (InvalidMessage invalidMessage) {
                    invalidMessage.printStackTrace();
                } catch (FieldNotFound fieldNotFound) {
                    fieldNotFound.printStackTrace();
                }
            }

        });

        browseLogFile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file");
            File selectedDirectory =
                    fileChooser.showOpenDialog(primaryStage);
            if (selectedDirectory == null) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Выберите пожалуйста файл с логами");
                alert.setResizable(true);
                alert.showAndWait();
            } else {
                filename.setText(selectedDirectory.getAbsolutePath());
            }
        });

        browseReportFile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file");
            File selectedDirectory =
                    fileChooser.showOpenDialog(primaryStage);
            if (selectedDirectory == null) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Выберите пожалуйста файл для сохранения менеджмента книжки");
                alert.setResizable(true);
                alert.showAndWait();
            } else {
                reportFile.setText(selectedDirectory.getAbsolutePath());
            }
        });
    }
}
