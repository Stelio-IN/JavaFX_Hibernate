package aaaaa;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class TelaPrincipalController implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    private Pessoa obj = new Pessoa();

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TreeTableColumn<Pessoa, String> colunaEmail;

    @FXML
    private TreeTableColumn<Pessoa, String> colunaId;

    @FXML
    private TreeTableColumn<Pessoa, String> colunaNome;

    @FXML
    private TreeTableColumn<Pessoa, String> colunaPassword;

    @FXML
    private ColorPicker paneColor;

    @FXML
    private TreeTableView<Pessoa> tabela;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPassword;

    private List<Object> listaClientes;
    private ObservableList<Pessoa> observableListe;
    private GenericController dao = new GenericController();

    @FXML
    void changeColor(ActionEvent event) {
        javafx.scene.paint.Color cor = paneColor.getValue();
        anchorpane.setBackground(new Background(new BackgroundFill(cor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    void editar(ActionEvent event) {
        Class<Pessoa> classe = Pessoa.class;
        obj.setId(Long.valueOf(txtId.getText()));
        obj.setEmail(txtEmail.getText());
        obj.setNome(txtNome.getText());
        obj.setPassword(txtPassword.getText());

        dao.Atualizar(classe, Long.valueOf(txtId.getText()), obj);
        JOptionPane.showMessageDialog(null, "Atualizado COm sucesso");
        txtId.setText("");
        txtEmail.setText("");
        txtNome.setText("");
        txtPassword.setText("");
        listar(event);
    }

    @FXML
    void inserir(ActionEvent event) throws IOException {
        TelaCadastro(event);
    }

    @FXML
    void listar(ActionEvent event) {
        Class<Pessoa> classe = Pessoa.class;
        List<Pessoa> lista = (List<Pessoa>) dao.listar(classe);

        colunaId.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        colunaEmail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
        colunaNome.setCellValueFactory(new TreeItemPropertyValueFactory<>("nome"));
        colunaPassword.setCellValueFactory(new TreeItemPropertyValueFactory<>("password"));

        observableListe = FXCollections.observableArrayList(lista);
        tabela.setRoot(new TreeItem<>()); // Limpar qualquer conteúdo anterior
        tabela.getRoot().getChildren().addAll(observableListe.stream().map(TreeItem::new).collect(Collectors.toList()));
    }

    @FXML
    void remover(ActionEvent event) {
        Class<Pessoa> classe = Pessoa.class;
        dao.removeFisico(classe, Long.valueOf(txtId.getText()));
        JOptionPane.showMessageDialog(null, "Removido COm sucesso");
        txtId.setText("");
        txtEmail.setText("");
        txtNome.setText("");
        txtPassword.setText("");
        listar(event);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> pegarLinhaSelecionada(newValue.getValue()));

    }

    @FXML
    void TelaCadastro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pegarLinhaSelecionada(Pessoa pessoa) {
        if (pessoa != null) {
            txtId.setText(String.valueOf(pessoa.getId()));
            txtEmail.setText(pessoa.getEmail());
            txtNome.setText(pessoa.getNome());
            txtPassword.setText(pessoa.getPassword());
        } else {
            txtId.setText("");
            txtEmail.setText("");
            txtNome.setText("");
            txtPassword.setText("");
        }
    }

}
