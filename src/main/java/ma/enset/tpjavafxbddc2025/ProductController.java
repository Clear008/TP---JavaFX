package ma.enset.tpjavafxbddc2025;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrix;
    @FXML
    private ListView<Product> listView;
    private ObservableList<Product> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setItems(data);
    }

    public void addProduct() {
        String name = textFieldNom.getText();
        try {
            double price = Double.parseDouble(textFieldPrix.getText());

            if (name.trim().isEmpty()) {
                showError("Veuillez renseigner le nom !");
                return;
            }

            data.add(new Product(name, price));

            clearFields();

        } catch (NumberFormatException e) {
            showError("Prix invalide !");
        }
    }

    public void deleteProduct() {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            showError("Veuillez sélectionner un produit !");
        } else {
            data.remove(index);
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    private void clearFields() {
        textFieldNom.setText("");
        textFieldPrix.setText("");
    }

}