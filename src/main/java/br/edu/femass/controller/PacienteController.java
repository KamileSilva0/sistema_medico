package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.model.Paciente;
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

public class PacienteController implements Initializable {

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    @FXML 
    private ListView<Paciente> listaPaciente;

    private PacienteDao pacienteDao = new PacienteDao();

    @FXML
    private void listaPaciente_mouseCliked(MouseEvent event){
        exibirDadosLista();
    }

    @FXML
    private void listaPaciente_keyPressed(KeyEvent event){
        exibirDadosLista();
    }

    private void exibirDadosLista(){
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;
        
        txtCPF.setText(paciente.getCpf());
        txtNome.setText(paciente.getNomePacienete());
        txtIdade.setText(paciente.getIdade());
        txtEndereco.setText(paciente.getEndereco());
        txtID.setText(paciente.getId().toString());
        txtTelefone.setText(paciente.getTelefones().get(0));
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;
        
        txtCPF.setText("");
        txtNome.setText("");
        txtIdade.setText("");
        txtEndereco.setText("");
        txtID.setText("");
        txtTelefone.setText("");
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;
        
        try{
        pacienteDao.excluir(paciente);
        mostrarPacientes();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        try{
        Paciente paciente = new Paciente(
            txtCPF.getText(), 
            txtNome.getText(), 
            txtIdade.getText(), 
            txtTelefone.getText()
        );
        paciente.setEndereco(txtEndereco.getText());

        txtID.setText(paciente.getId().toString());
        if (pacienteDao.gravar(paciente)==false){
            JavaFx.showMessage("Não foi possível registrar novo paciente.");
            return;
        }
        mostrarPacientes();
        }catch(Exception exception){
            JavaFx.showMessage(exception.getMessage());
        }
    }
    public void mostrarPacientes(){
        try{
        ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.mostrar());
        listaPaciente.setItems(data);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0lo, ResourceBundle arg1) {
        mostrarPacientes();
    }
    
}
