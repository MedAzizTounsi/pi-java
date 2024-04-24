package controllers;

import entities.Services;
import entities.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.TypeServiceService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherTypeService {
    TypeServiceService typeServicce = new TypeServiceService();
    @FXML
    private ListView<TypeService> typeServiceListView;
    private ObservableList<TypeService> observableList;
    @FXML
    private Button actualiserTypeServiceButton;

    // Méthode appelée lors du clic sur le bouton Actualiser
    @FXML
    void actualiserTypeServiceList(ActionEvent event) {
        refreshTypeServiceList(); // Appel de la fonction de rafraîchissement des types de services
    }
    // Fonction pour rafraîchir la liste des types de services
    public void refreshTypeServiceList() {
        try {
            afficherTypeServices();
            // Logique pour rafraîchir la liste des types de services depuis la base de données
            // Assurez-vous d'adapter cette partie selon votre implémentation
        } catch (Exception e) {
            // Gérer les exceptions de manière appropriée
        }
    }

    private void afficherTypeServices() throws SQLException {
        List<TypeService> services = typeServicce.getAll();
        observableList = FXCollections.observableList(services);
        typeServiceListView.setItems(observableList);
        typeServiceListView.refresh();
    }



    @FXML
    void initialize() {
        try {
            afficherTypeServices();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterTypeService(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterTypeService.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Ajouter Service");
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();

            // Close the current stage (optional)
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        typeServiceListView.refresh();
    }
    public void modifierTypeService(ActionEvent actionEvent) {
        TypeService typeserviceSelecionnee = typeServiceListView.getSelectionModel().getSelectedItem();
        if (typeserviceSelecionnee != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierTypeService.fxml"));
                Parent root = loader.load();
                System.out.println("FXML file loaded successfully.");
                ModifierTypeSerService controller = loader.getController();
                System.out.println("Controller initialized.");

                controller.setTypeService(typeserviceSelecionnee);
                System.out.println("Data initialized in controller.");

                Stage stage = new Stage();
                stage.setTitle("Liste des Types");
                stage.setScene(new Scene(root));

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun Service sélectionné.");
        }
        typeServiceListView.refresh();
    }
    public void supprimerTypeService(ActionEvent actionEvent) {
        TypeService typeServiceSelectionne = typeServiceListView.getSelectionModel().getSelectedItem();
        if (typeServiceSelectionne != null) {
            try {
                typeServicce.supprimer(typeServiceSelectionne.getId());
                System.out.println("Service supprimé avec succès !");

                // Supprimer l'appartement de la liste affichée dans la table
                typeServiceListView.getItems().remove(typeServiceSelectionne);

                // Afficher une confirmation à l'utilisateur
                afficherAlerteErreur("Suppression réussie", "Le Service a été supprimé avec succès.");
            } catch (SQLException e) {
                // Gérer l'exception pour les contraintes de clé étrangère
                afficherAlerteErreur("Erreur de suppression", "Impossible de supprimer ce Service ");
            } catch (Exception e) {
                // Gérer les autres exceptions
                afficherAlerteErreur("Erreur", "Une erreur s'est produite lors de la suppression de Service : " + e.getMessage());
            }
        } else {
            afficherAlerteErreur("Sélection requise", "Veuillez sélectionner un Service à supprimer.");
        }
        typeServiceListView.refresh();
    }
    private void afficherAlerteErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
