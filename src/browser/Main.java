/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author rajesh
 */
public class Main extends Application {
    
    TabPane root;
            
    @Override
    public void start(Stage stage) throws IOException {                
        Parent browser = FXMLLoader.load(getClass().getResource("Browser.fxml"));
        Tab browserTab = new Tab("New Tab", browser);
        Tab addTab = new Tab("+", null);
        addTab.setClosable(false);        
        addTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                addNewTab();
            }
        });
        root = new TabPane(browserTab, addTab);
        Scene scene = new Scene(root, getVisualScreenWidth() / 1.2, getVisualScreenHeight() / 1.2);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("BlockHives");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
                
    }

    final void addNewTab() {
        try {
            Parent browser = FXMLLoader.load(getClass().getResource("Browser.fxml"));
            Tab browserTab = new Tab("New Tab", browser);
            root.getTabs().add(root.getTabs().size() - 1, browserTab);
            root.getSelectionModel().select(browserTab);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
	 * Gets the visual screen width.
	 *
	 * @return The screen <b>Width</b> based on the <b>visual bounds</b> of the Screen.These bounds account for objects in the native windowing system
	 *         such as task bars and menu bars. These bounds are contained by Screen.bounds.
	 */
	public static double getVisualScreenWidth() {
		return Screen.getPrimary().getVisualBounds().getWidth();
	}
	
	/**
	 * Gets the visual screen height.
	 *
	 * @return The screen <b>Height</b> based on the <b>visual bounds</b> of the Screen.These bounds account for objects in the native windowing system
	 *         such as task bars and menu bars. These bounds are contained by Screen.bounds.
	 */
	public static double getVisualScreenHeight() {
		return Screen.getPrimary().getVisualBounds().getHeight();
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
