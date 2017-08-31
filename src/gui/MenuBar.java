package gui;

import java.net.URL;
import java.util.ResourceBundle;

import controls.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * This class sets up the menu bar as an HBox that holds several different buttons
 * and dropdown boxes that allow for different visual options and resource files
 * to be utilized from the front end. All XML writing/reading is accessible here.
 * Language setting is handled here. 
 * 
 * Created February 23rd, 2017
 * 
 * @author Jake Conroy
 *
 */

public class MenuBar extends HBox {
	private static final String HELP_PAGE = "HelpPage.html";
	private static final String HELP_MENU = "HelpMenu";
	private static final String HELP = "Help";
	private static final String SET_LANGUAGES = "SetLanguage";
	private static final String LANGUAGES = "Languages";
	private static final String SAVE_LIB = "SaveLib";
	private static final String SAVE_VISUAL = "SaveVis";
	private static final String LOAD_LIB = "LoadLib";
	private static final String LOAD_VISUAL = "LoadVis";
	private static final String SHOW_HIDE = "ShowHide";
	private ComboBox<String> cb;
	private Button sendLanguage = new Button();
	private Button btn_help = new Button();
	private Button saveLibrary = new Button();
	private Button saveVisual = new Button();
	private Button loadLibrary = new Button();
	private Button loadVisual = new Button();
	private Button showHide = new Button();
	private Controller ct;
	public static final ObservableList<String> LANGUAGE_OPTIONS = 
			FXCollections.observableArrayList(
					"English",
					"Chinese",
					"French",
					"Spanish",
					"German",
					"Italian",
					"Portuguese",
					"Russian"
					);
	public MenuBar(Controller c) {
		ct = c;
		makeItems();
		makeMenuBar();
	}
	
	public void setLanguage(ResourceBundle resource){
		cb.setPromptText(resource.getString(LANGUAGES));
		sendLanguage.setText(resource.getString(SET_LANGUAGES));
		btn_help.setText(resource.getString(HELP));
		saveLibrary.setText(resource.getString(SAVE_LIB));
		saveVisual.setText(resource.getString(SAVE_VISUAL));
		loadLibrary.setText(resource.getString(LOAD_LIB));
		loadVisual.setText(resource.getString(LOAD_VISUAL));
		showHide.setText(resource.getString(SHOW_HIDE));
	}
	
	private void makeItems() {
		cb = new ComboBox<String>(LANGUAGE_OPTIONS);
		cb.setPromptText(ct.getResourceBundle().getString(LANGUAGES));
		sendLanguage.setText(ct.getResourceBundle().getString(SET_LANGUAGES));
		sendLanguage.setPrefSize(200, 25);
		sendLanguage.setOnAction(e -> sendLanguageButtonClick());
		btn_help.setText(ct.getResourceBundle().getString(HELP));
		btn_help.setOnAction(e -> createHelpStage());
		saveLibrary.setText(ct.getResourceBundle().getString(SAVE_LIB));
		saveLibrary.setOnAction(e -> saveLibrary());
		saveVisual.setText(ct.getResourceBundle().getString(SAVE_VISUAL));
		saveVisual.setOnAction(e -> saveVisual());
		loadLibrary.setText(ct.getResourceBundle().getString(LOAD_LIB));
		loadLibrary.setOnAction(e -> loadLibrary());
		loadVisual.setText(ct.getResourceBundle().getString(LOAD_VISUAL));
		loadVisual.setOnAction(e -> loadVisual());
		showHide.setText(ct.getResourceBundle().getString(SHOW_HIDE));
		showHide.setOnAction(e -> showHide());
	}
	
	private void showHide() {
		ct.getMyUI().reset();
	}
	private void createHelpStage() {
		Group root = new Group();
		WebView browser = new WebView();
		Scene helpScene = new Scene(root);
		Stage helpStage = new Stage();
		helpStage.setTitle(ct.getResourceBundle().getString(HELP_MENU));
		helpStage.setScene(helpScene);
		URL url = getClass().getResource(HELP_PAGE);
		browser.getEngine().load(url.toExternalForm());
		root.getChildren().add(browser);
		helpStage.show();
	}
	
	private void sendLanguageButtonClick() {
		if(cb.getValue()==null){
			return;
		}
		ct.setLanguage(cb.getValue());
	}
	
	private void saveLibrary() {
		ct.saveLibrary();
	}
	
	private void loadLibrary() {
		ct.loadLibrary();
	}
	private void loadVisual() {
		ct.loadVisual();
	}

	private void saveVisual() {
		ct.saveVisual();
	}

	private void makeMenuBar() {
		this.getChildren().addAll(cb, sendLanguage, btn_help, saveLibrary, loadLibrary, saveVisual, loadVisual, showHide);
	}
}