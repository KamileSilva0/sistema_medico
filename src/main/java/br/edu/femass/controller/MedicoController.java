package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.utils.JavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MedicoController implements Initializable {

   
    @FXML
    private TextField txtMedico;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtCrm;

    @FXML
    private TextField txtTelefone;

    @FXML
    private ComboBox<Especialidade> cboEspecialidade;

    @FXML
    private ListView<Medico> listaMedico;

    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();
    
    @FXML
    private void listaMedico_mouseCliked(MouseEvent event){
        exibirDadosLista();
    }

    @FXML
    private void listaMedico_keyPressed(KeyEvent event){
        exibirDadosLista();
    }

    private void exibirDadosLista(){
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;
        
        txtMedico.setText(medico.getNomeMedico());
        txtCrm.setText(medico.getCrm());
        txtID.setText(medico.getId().toString());
        txtTelefone.setText(medico.getTelefones().get(0));
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;
        
        txtMedico.setText("");
        txtCrm.setText("");
        txtID.setText("");
        txtTelefone.setText("");
    }

    
    @FXML
    private void btnExcluir(ActionEvent event) {
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;
        
        try{
        medicoDao.excluir(medico);
        mostrarMedicos();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        try{
        Medico medico = new Medico(
            txtCrm.getText(), 
            txtMedico.getText(), 
            txtTelefone.getText()
        );

        txtID.setText(medico.getId().toString());
        if (medicoDao.gravar(medico)==false){
            JavaFx.showMessage("Não foi possível registrar novo médico.");
            return;
        }
        mostrarMedicos();
        }catch(Exception exception){
            JavaFx.showMessage(exception.getMessage());
        }
    }
    public void mostrarMedicos(){
        try{
        ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.mostrar());
        listaMedico.setItems(data);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
    public void preencherCombo(){
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.mostrar());
            cboEspecialidade.setItems(data);
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarMedicos();
        preencherCombo();
    }
    
}
