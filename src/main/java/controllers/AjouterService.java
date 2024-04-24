package controllers;

import entities.Services;
import entities.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServicesService;
import services.TypeServiceService;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AjouterService {

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField nomServiceField;

    @FXML
    private TextField prixField;
    ServicesService serviceService = new ServicesService();
    @FXML
    private ListView<TypeService> typeServiceListView;
    private ObservableList<TypeService> observableList;

    private TypeServiceService typeServiceService;
    TypeServiceService typeServicce = new TypeServiceService();
    public AjouterService() {
        typeServiceService = new TypeServiceService();
    }
    private void afficherTypeServices() throws SQLException{
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







    public void ajouterService(javafx.event.ActionEvent actionEvent) throws SQLException {

        String nomService = nomServiceField.getText();
        String prixText = prixField.getText();
        String description = descriptionField.getText();
        TypeService typeService = typeServiceListView.getSelectionModel().getSelectedItem();
        System.out.println(typeService);

        int prix = Integer.parseInt(prixText);

        // Créer un nouveau service avec les informations fournies
        Services nouveauService = new Services();
        nouveauService.setNom_service(nomService);
        nouveauService.setPrix(prix);
        nouveauService.setDescription(description);
        nouveauService.setTypeService(typeService);

        try {
            // Appeler la méthode ajouter du serviceService pour ajouter le nouveau service à la base de données
            serviceService.ajouter(nouveauService);

            // Afficher une alerte pour indiquer que le service a été ajouté avec succès
            afficherAlerte("Succès", "Le service a été ajouté avec succès.");

            // Effacer les champs de saisie
            nomServiceField.clear();
            prixField.clear();
            descriptionField.clear();
            typeServiceListView.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            // Gérer les erreurs éventuelles lors de l'ajout du service
            afficherAlerte("Erreur", "Une erreur est survenue lors de l'ajout du service : " + e.getMessage());
        }

    }



    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    private boolean isValidInput(String nomService) {

        return nomService.matches("[a-zA-Z]{1,10}");
    }

}
