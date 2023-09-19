/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package aaaaa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author steli
 */
public class FXMLDocumentController implements Initializable {

   @FXML
    private Button btnClose;
   
   @FXML
   private AnchorPane scenePane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPassword;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    void TelaLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    void close(ActionEvent event) {
            
         Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
         alerta.setTitle("FECHAR");
         alerta.setHeaderText("Quer realment Fechar");
         alerta.setContentText("Deseja salvar antes de Fechar");
         if(alerta.showAndWait().get() == ButtonType.OK){
        stage =(Stage) scenePane.getScene().getWindow();
         System.out.println("Exit exito");
         stage.close();
         }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

        Pessoa aa = new Pessoa();
        GenericController bb = new GenericController();
        aa.setNome(txtNome.getText());
        aa.setEmail(txtEmail.getText());
        aa.setPassword(txtPassword.getText());
        bb.add(aa);
        JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso:");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
