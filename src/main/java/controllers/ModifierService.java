package controllers;

import entities.Services;
import entities.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServicesService;
import services.TypeServiceService;

import java.sql.SQLException;
import java.util.List;

public class ModifierService {
    Services serviceSelecionnee = new Services();
    @FXML
    private TextField descriptionField;

    @FXML
    private TextField nomServiceField;

    @FXML
    private TextField prixField;

    @FXML
    private ListView<TypeService> typeServiceListView;
    TypeServiceService typeServiceService = new TypeServiceService();
    ServicesService serviceService = new ServicesService();
    public void setService(Services service) {
        this.serviceSelecionnee = service;
        if (service != null) {
            // Remplir les champs avec les informations du service sélectionné
            nomServiceField.setText(service.getNom_service());
            prixField.setText(String.valueOf(service.getPrix()));
            descriptionField.setText(service.getDescription());
            // Charger les types de service associés au service sélectionné dans la ListView
            try {
                List<TypeService> typesDeService = typeServiceService.getAll();
                ObservableList<TypeService> observableList = FXCollections.observableArrayList(typesDeService);
                typeServiceListView.setItems(observableList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




@FXML
public void ModifierService(ActionEvent actionEvent) {
        if (serviceSelecionnee != null) {
            try {
                // Récupérer le type de service sélectionné dans la ListView
                TypeService typeServiceSelectionne = typeServiceListView.getSelectionModel().getSelectedItem();
                if (typeServiceSelectionne != null) {
                    // Mettre à jour les informations du service sélectionné avec les valeurs des champs
                    serviceSelecionnee.setNom_service(nomServiceField.getText());
                    serviceSelecionnee.setPrix(Integer.parseInt(prixField.getText()));
                    serviceSelecionnee.setTypeService(typeServiceSelectionne);
                    serviceSelecionnee.setDescription(descriptionField.getText());

                    // Appeler la méthode modifier du serviceService pour mettre à jour le service dans la base de données
                    serviceService.modifier(serviceSelecionnee);

                    // Si la modification réussit, vous pouvez mettre à jour l'affichage ou effectuer d'autres actions nécessaires
                    // Par exemple, pour mettre à jour la liste affichée, vous pouvez appeler :
                    // afficherServices();

                } else {
                    System.out.println("Veuillez sélectionner un type de service.");
                }
            } catch (SQLException e) {
                // Gérer les erreurs éventuelles lors de la modification du service
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un prix valide.");
            }
        }
    }
}
