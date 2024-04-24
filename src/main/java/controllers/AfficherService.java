package controllers;

import entities.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.ServicesService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherService {
  ServicesService serviceService = new ServicesService();
    private ObservableList<Services> observableList;

    @FXML
    private ListView<Services> listViewService;
    private void afficherServices() throws SQLException{
        List<Services> services = serviceService.getAll();
        observableList = FXCollections.observableList(services);
        listViewService.setItems(observableList);
        listViewService.refresh();
    }

    @FXML
    void initialize() {
        try {
            afficherServices();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void ajouterService(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterService.fxml"));
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
        listViewService.refresh();
    }

    @FXML
    void modifierService(ActionEvent event) {
        Services serviceSelecionnee = listViewService.getSelectionModel().getSelectedItem();
        if (serviceSelecionnee != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierService.fxml"));
                Parent root = loader.load();
                System.out.println("FXML file loaded successfully.");
                ModifierService controller = loader.getController();
                System.out.println("Controller initialized.");

                controller.setService(serviceSelecionnee);
                System.out.println("Data initialized in controller.");

                Stage stage = new Stage();
                stage.setTitle("Liste des Factures");
                stage.setScene(new Scene(root));

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun Service sélectionné.");
        }
        listViewService.refresh();
    }

    @FXML
    void supprimerService(ActionEvent event) {
        Services ServiceSelectionne = listViewService.getSelectionModel().getSelectedItem();
        if (ServiceSelectionne != null) {
            try {
                serviceService.supprimer(ServiceSelectionne.getId());
                System.out.println("Service supprimé avec succès !");

                // Supprimer l'appartement de la liste affichée dans la table
                listViewService.getItems().remove(ServiceSelectionne);

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
    }
    private void afficherAlerteErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
