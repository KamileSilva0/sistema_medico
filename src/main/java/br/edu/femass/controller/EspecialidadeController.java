package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.model.Especialidade;
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

public class EspecialidadeController implements Initializable {

    @FXML
    private TextField txtEspecialidade;

    @FXML
    private TextField txtCod;

    @FXML
    private ListView<Especialidade> listaEspecialidade;

    private EspecialidadeDao especialidadeDao = new EspecialidadeDao();

    @FXML
    private void listaEspecialidade_mouseCliked(MouseEvent event){
        exibirDadosLista();
    }

    @FXML
    private void listaEspecialidade_keyPressed(KeyEvent event){
        exibirDadosLista();
    }

    private void exibirDadosLista(){
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;
        
        txtEspecialidade.setText(especialidade.getNomeEspecialiade());
        txtCod.setText(especialidade.getCodEspecialidade().toString());
    }

    @FXML
    private void btnNovo(ActionEvent event){
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;
        
        txtEspecialidade.setText("");
        txtCod.setText("");
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;
        
        try{
        especialidadeDao.excluir(especialidade);
        mostrarEspecialidades();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        try{
        Especialidade especialidade = new Especialidade(txtEspecialidade.getText());

        txtCod.setText(especialidade.getCodEspecialidade().toString());
        if (especialidadeDao.gravar(especialidade)==false){
            JavaFx.showMessage("Não foi possível registrar nova especialidade.");
            return;
        }
        mostrarEspecialidades();
        }catch(Exception exception){
            JavaFx.showMessage(exception.getMessage());
        }
    }
    public void mostrarEspecialidades(){
        try{
        ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.mostrar());
        listaEspecialidade.setItems(data);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }


    @Override
    public void initialize(URL arg0lo, ResourceBundle arg1) {
        mostrarEspecialidades();
    }
    
}
