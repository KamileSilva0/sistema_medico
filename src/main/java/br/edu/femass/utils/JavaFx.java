package br.edu.femass.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JavaFx {

    public static void showMessage(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(mensagem);
        alert.show();
    }
    
}
