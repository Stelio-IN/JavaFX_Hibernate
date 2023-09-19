package aaaaa;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    
    @FXML
    void Tela_Cadastro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Tela_Principal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void validar(ActionEvent event) {
        GenericController bb = new GenericController();
        Pessoa aa = (Pessoa) bb.logarEmail(txtEmail.getText());

        if (aa != null && aa.getPassword().equals(txtPassword.getText())) {
            try {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                  alerta.setTitle("Login");
         alerta.setHeaderText("Login efetuado com sucesso!!!!");
        // alerta.setContentText("Deseja salvar antes de Fechar");
         if(alerta.showAndWait().get() == ButtonType.OK){
    
         Tela_Principal(event);
         }
               
            } catch (IOException e) {
                e.printStackTrace(); // Trate ou registre erros adequadamente
            }
        } else {
            // Exiba uma mensagem de erro ao usuário, informando que as credenciais são inválidas.
        }
    }

}
