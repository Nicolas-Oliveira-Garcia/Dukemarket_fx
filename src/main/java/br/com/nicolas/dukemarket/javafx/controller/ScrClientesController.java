/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.nicolas.dukemarket.javafx.controller;

import br.com.nicolas.dukemarket.javafx.DAO.ClienteDAO;
import br.com.nicolas.dukemarket.javafx.model.Cliente;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author qualifica
 */
public class ScrClientesController implements Initializable {

    
    @FXML
    TableView <Cliente> tblCliente;
    
    @FXML
    TableColumn <Cliente, Integer> tcoIdCliente;
    
    @FXML
    TableColumn <Cliente, String> tcoNome;
    
    @FXML
    TableColumn <Cliente, String> tcoEndereco;
    
    @FXML
    TableColumn <Cliente, String> tcoCidade;
    
    @FXML
    TableColumn <Cliente, String> tcoUf;
    
    @FXML
    TableColumn <Cliente, String> tcoCep;
    
    @FXML
    TableColumn <Cliente, String> tcoTelefone;
    
    @FXML
    TableColumn <Cliente, String> tcoCelular;
    
    @FXML
    TableColumn <Cliente, String> tcoEmail;
    
    @FXML
    TextField txtIdCliente;
    
    @FXML
    TextField txtNome;
    
    @FXML
    TextField txtEndereco;
    
    @FXML
    TextField txtCidade;
    
    @FXML
    TextField txtUf;
    
    @FXML
    TextField txtCep;
    
    @FXML
    TextField txtTelefone;
    
    @FXML
    TextField txtCelular;
    
    @FXML
    TextField txtEmail;
    
    
    ClienteDAO cDAO;
    Cliente pClicked;
    
    boolean flagNovo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindColumns();
        
        carregaDados();
    }    
    
    private void bindColumns() {
        tcoIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcoIdCliente.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcoNome.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tcoEndereco.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tcoCidade.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoUf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        tcoUf.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcoCep.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tcoTelefone.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tcoCelular.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcoEmail.setStyle("-fx-alignment: CENTER_RIGHT;");
        
    }
    
    public void carregaDados(){
        this.cDAO = new ClienteDAO();
        
        this.tblCliente.getItems().setAll(cDAO.getResults());
    }
    
    @FXML
    private void btnNovoClickCliente(){
        txtIdCliente.setText("auto");
        txtIdCliente.setEditable(false);
        
        txtNome.setText("");
        txtNome.requestFocus();
        
        txtEndereco.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        txtCep.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        this.flagNovo = true;
    }
    // captura de clique na tabela
    @FXML
    public void tblClienteOnMouseClicked(){
        this.pClicked = tblCliente.getSelectionModel().getSelectedItem();
        
        if (pClicked != null){
            txtIdCliente.setText(String.valueOf(pClicked.getId()));
            txtIdCliente.setEditable(false);
            
            txtNome.setText(pClicked.getNome());
            txtEndereco.setText(pClicked.getEndereco());
            txtCidade.setText(pClicked.getCidade());
            txtUf.setText(pClicked.getUf());
            txtCep.setText(pClicked.getCep());
            txtTelefone.setText(pClicked.getTelefone());
            txtCelular.setText(pClicked.getCelular());
            txtEmail.setText(pClicked.getEmail());
            
            this.flagNovo = false;
        }
    }
    
    @FXML
    public void btnExcluirClickCliente(){
        if (this.pClicked != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Exclusão de Produto?");
            alert.setHeaderText(pClicked.getNome());
            alert.setContentText("Tem certeza que deseja excluir ?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.cDAO.delete(this.pClicked.getId());
                
                this.tblCliente.getItems().remove(this.pClicked);
            }    
            this.tblCliente.getSelectionModel().clearSelection();
            pClicked = null;    
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ops!");
            alert.setHeaderText("Atenção");
            alert.setContentText("Você deve selecionar um registro antes de excluí-los");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    public void btnSalvarClickCliente(){
        Cliente c = new Cliente();
        
        c.setNome(txtNome.getText());
        c.setEndereco(txtEndereco.getText());
        c.setCidade(txtCidade.getText());
        c.setUf(txtUf.getText());
        c.setCep(txtCep.getText());
        c.setTelefone(txtTelefone.getText());
        c.setCelular(txtCelular.getText());
        c.setEmail(txtEmail.getText());
        
        if (this.flagNovo){
            this.cDAO.create(c);
        } else {
            c.setId (Integer.parseInt(txtIdCliente.getText()));
            this.cDAO.update(c);
        }
        this.carregaDados();
    }  
    
}
