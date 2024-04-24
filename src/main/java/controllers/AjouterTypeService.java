package controllers;

import entities.TypeService;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import services.TypeServiceService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.SQLException;

public class AjouterTypeService {

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField typeServiceField;

    @FXML
    void ajouterTypeService(ActionEvent event) {
        String typeService = typeServiceField.getText();
        String description = descriptionField.getText();

        // Vérifier si le type de service est valide
        if (!isValidInput(typeService)) {
            afficherAlerte("Type de service", "Le type de service doit contenir uniquement des caractères et avoir une longueur maximale de 30.");
            return;
        }

        // Vérifier si la description est valide
        if (!isValidInput(description)) {
            afficherAlerte("Description", "La description doit contenir uniquement des caractères et avoir une longueur maximale de 30.");
            return;
        }

        TypeServiceService typeServiceService = new TypeServiceService();
        try {
            typeServiceService.ajouter(new TypeService(typeService, description));
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur
        }
        // Effacer les champs après l'ajout
        typeServiceField.clear();
        descriptionField.clear();

    }

    private void afficherAlerte(String champ, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Erreur dans le champ " + champ + ": " + message);
        alert.showAndWait();
    }
    private boolean isValidInput(String input) {
        return input.matches("[a-zA-Z]{1,30}");
    }
}
