package controllers;

import entities.Services;
import entities.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.TypeServiceService;

import java.sql.SQLException;
import java.util.List;

public class ModifierTypeSerService {

    @FXML
    private TextField descriptionField;
    TypeService typeserviceSelecionnee = new TypeService();


    @FXML
    private TextField typeServiceField;
    public void setTypeService(TypeService typeService) {
        this.typeserviceSelecionnee = typeService;
        if (typeService != null) {
            descriptionField.setText(typeService.getDescription());
            typeServiceField.setText(String.valueOf(typeService.getType_service()));
            // Charger les types de service associés au service sélectionné dans la ListView

        }
    }
    @FXML
    void modifierTypeService(ActionEvent event) {
        String nouveauTypeService = typeServiceField.getText();
        String nouvelleDescription = descriptionField.getText();

            typeserviceSelecionnee.setType_service(nouveauTypeService);
            typeserviceSelecionnee.setDescription(nouvelleDescription);

            try {
                // Appeler la méthode de mise à jour du TypeServiceService pour mettre à jour le type de service
                TypeServiceService typeServiceService = new TypeServiceService();
                typeServiceService.modifier(typeserviceSelecionnee);

                // Afficher une alerte pour indiquer que le type de service a été modifié avec succès
                afficherAlerte("Succès", "Le type de service a été modifié avec succès.");

                // Effacer les champs de saisie
                typeServiceField.clear();
                descriptionField.clear();
            } catch (SQLException e) {
                // Gérer les erreurs éventuelles lors de la mise à jour du type de service
                afficherAlerte("Erreur", "Une erreur est survenue lors de la modification du type de service : " + e.getMessage());
            }
        }

    private void afficherAlerte(String champ, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Erreur dans le champ " + champ + ": " + message);
        alert.showAndWait();
    }


}
