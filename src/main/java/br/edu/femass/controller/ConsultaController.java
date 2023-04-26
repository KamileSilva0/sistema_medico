package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.ConsultaDao;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoDeSaudeDao;
import br.edu.femass.model.Consulta;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoDeSaude;
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

public class ConsultaController implements Initializable {

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtHora;

    @FXML
    private TextField txtSala;

    @FXML
    private ListView<Consulta> listaConsulta;

    @FXML
    private ComboBox<Especialidade> cboEspecialidade;

    @FXML
    private ComboBox<Medico> cboMedico;

    @FXML
    private ComboBox<Paciente> cboPaciente;

    @FXML
    private ComboBox<PlanoDeSaude> cboPlano;

    private Dao<Consulta> consultaDao = new ConsultaDao();
    private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();
    private Dao<Paciente> pacienteDao = new PacienteDao();
    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<PlanoDeSaude> planoDeSaudeDao = new PlanoDeSaudeDao();

    @FXML
    private void listaConsulta_mouseClicked(MouseEvent event) {
        exibirDadosLista();
    }

    @FXML
    private void listaConsulta_keyPressed(KeyEvent event) {
        exibirDadosLista();
    }

    private void exibirDadosLista() {
        Consulta consulta = listaConsulta.getSelectionModel().getSelectedItem();
        if (consulta == null)
            return;

        txtSala.setText(consulta.getSala());
        txtData.setText(consulta.getData().format(null));
        txtHora.setText(consulta.getHora().format(null));
    }

    @FXML
    private void btnNovo(ActionEvent event) {
        Consulta consulta = listaConsulta.getSelectionModel().getSelectedItem();
        if (consulta == null)
            return;

        txtSala.setText("");
        txtData.setText("");
        txtHora.setText("");
    }

    @FXML
    private void btnExcluir(ActionEvent event) {
        Consulta consulta = listaConsulta.getSelectionModel().getSelectedItem();
        if (consulta == null)
            return;

        try {
            consultaDao.excluir(consulta);
            mostrarConsultas();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnSalvar(ActionEvent event) {
        try {
            Consulta consulta = new Consulta(txtSala.getText());

            txtData.getText();
            txtHora.getText();

            if (consultaDao.gravar(consulta) == false) {
                JavaFx.showMessage("Não foi possível registrar nova consulta.");
                return;
            }
            mostrarConsultas();

        } catch (Exception exception) {
            JavaFx.showMessage(exception.getMessage());
        }
        
    }

    public void mostrarConsultas() {
        try {
            ObservableList<Consulta> data = FXCollections.observableArrayList(consultaDao.mostrar());
            listaConsulta.setItems(data);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void preencherComboEspecialidade(){
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.mostrar());
            cboEspecialidade.setItems(data);
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }
    
    public void preencherComboPaciente(){
        try{
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.mostrar());
            cboPaciente.setItems(data);
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }

    public void preencherComboPlanoDeSaude(){
        try{
            ObservableList<PlanoDeSaude> data = FXCollections.observableArrayList(planoDeSaudeDao.mostrar());
            cboPlano.setItems(data);
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }

    public void preencherComboMedico(){
        try{
            ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.mostrar());
            cboMedico.setItems(data);
            }catch(Exception exception){
                exception.printStackTrace();
            }
    }

    @Override
    public void initialize(URL arg0lo, ResourceBundle arg1) {
        mostrarConsultas();
        preencherComboEspecialidade();
        preencherComboPaciente();
        preencherComboPlanoDeSaude();
        preencherComboMedico();
    }

}
