package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PlanoDeSaudeDao;
import br.edu.femass.model.PlanoDeSaude;
import br.edu.femass.utils.JavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanoDeSaudeController implements Initializable {

    @FXML
    private TextField txtPlano;

    @FXML
    private TextField txtTelefone;

    @FXML
    private ListView<PlanoDeSaude> listaPlano;

    private Dao<PlanoDeSaude> planoDeSaudeDao = new PlanoDeSaudeDao();
    
    @FXML
    private void listaPlano_mouseClicked(MouseEvent event){
        exibirDadosLista();
    }

    @FXML
    private void listaPlano_keyPressed(KeyEvent event){
        exibirDadosLista();
    }

    private void exibirDadosLista(){
        PlanoDeSaude plano = listaPlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;
        
        txtPlano.setText(plano.getNomePlano());
        txtTelefone.setText(plano.getTelefones().get(0));
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        PlanoDeSaude plano = listaPlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;
        
        txtPlano.setText("");
        txtTelefone.setText("");
    }

    
    @FXML
    private void btnExcluir(ActionEvent event) {
        PlanoDeSaude plano = listaPlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;
        
        try{
        planoDeSaudeDao.excluir(plano);
        mostrarPlanos();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        try{
        PlanoDeSaude plano = new PlanoDeSaude(
            txtPlano.getText(),
            txtTelefone.getText()
        );
        if (planoDeSaudeDao.gravar(plano)==false){
            JavaFx.showMessage("Não foi possível registrar novo Plano de Saúde.");
            return;
        }
        mostrarPlanos();
        }catch(Exception exception){
            JavaFx.showMessage(exception.getMessage());
        }
    }
    public void mostrarPlanos(){
        try{
        ObservableList<PlanoDeSaude> data = FXCollections.observableArrayList(planoDeSaudeDao.mostrar());
        listaPlano.setItems(data);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarPlanos();
    }
    
}
