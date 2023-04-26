package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Paciente.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();

        stage.setTitle("Paciente");
        stage.setScene(scene);
        stage.show();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleButtonActionEspecialidades(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();

        stage.setTitle("Especialidade");
        stage.setScene(scene);
        stage.show();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }
    @FXML
    private void handleButtonActionMedicos(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Medico.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
    
            stage.setTitle("Medico");
            stage.setScene(scene);
            stage.show();
            } catch(Exception exception){
                exception.printStackTrace();
            }
    }
    @FXML
    private void handleButtonActionPlanos(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PlanoDeSaude.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
    
            stage.setTitle("Plano de Súde");
            stage.setScene(scene);
            stage.show();
            } catch(Exception exception){
                exception.printStackTrace();
            }
    }

    @FXML
    private void handleButtonActionConsultas(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Consulta.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
    
            stage.setTitle("Consulta");
            stage.setScene(scene);
            stage.show();
            } catch(Exception exception){
                exception.printStackTrace();
            }
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
}
