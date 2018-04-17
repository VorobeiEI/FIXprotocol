package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
            if (filename.getText() == null || reportFile.getText() == null) {
                fileReaderService = new FileReaderService("fix.txt");

                fileWriterService = new FileWriterService("default.txt");
            } else {
                fileReaderService = new FileReaderService(filename.getText());

                fileWriterService = new FileWriterService(reportFile.getText());
            }

            controller = new Controller(fileReaderService, fileWriterService);
            try {
                controller.startManagedBook();
            } catch (InvalidMessage invalidMessage) {
                invalidMessage.printStackTrace();
            } catch (FieldNotFound fieldNotFound) {
                fieldNotFound.printStackTrace();
            }
        });

        browseLogFile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file");
            File selectedDirectory =
                    fileChooser.showOpenDialog(primaryStage);
            if (selectedDirectory == null) {
                filename.setText("fix.txt");
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
                reportFile.setText("default.txt");
            } else {
                reportFile.setText(selectedDirectory.getAbsolutePath());
            }
        });
    }
}
