/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exalttech.trex.ui;

import com.exalttech.trex.application.TrexApp;
import com.exalttech.trex.util.Util;
import java.io.File;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.testfx.framework.junit.ApplicationTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Base class for automation UI test
 *
 * @author Georgekh
 */
public class UIBaseTest extends UITestsServices {

    private static final String APP_DATA_PATH = File.separator + "TRex";

    @BeforeTest
    public void setUpClass() throws Exception {

        // remove appData folder
        File trexDirectory = new File(System.getenv("LOCALAPPDATA") + APP_DATA_PATH);
        if (trexDirectory.exists()) {
            FileUtils.deleteQuietly(trexDirectory);
        }
        ApplicationTest.launch(TrexApp.class);

    }

    @Override
    public void start(Stage stage) throws Exception {
        TrexApp.setPrimaryStage(stage);
        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(page);
        scene.getStylesheets().add(TrexApp.class.getResource("/styles/mainStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("TRex");
        stage.setResizable(true);
        stage.setMinWidth(1100);
        stage.setMinHeight(670);
        stage.show();
    }

    @AfterTest
    public void releaseAfterEachTest() throws TimeoutException {
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

}
