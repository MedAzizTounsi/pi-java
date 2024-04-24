package tests;

import entities.Services;
import entities.TypeService;
import services.ServicesService;
import services.TypeServiceService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {
        TypeServiceService typeServiceService = new TypeServiceService();
List<TypeService> typeServices = typeServiceService.getAll();
        System.out.println(typeServices);
        // Ajouter un TypeService
       //      TypeService typeServiceToAdd = new TypeService("Type1", "Description1");
        //     typeServiceService.ajouter(typeServiceToAdd);


        // Récupérer tous les TypeService
       // List<TypeService> typeServices = typeServiceService.getAll();
        //for (TypeService typeService : typeServices) {
           // System.out.println(typeService);
        //}
        // Récupérer un TypeService par son ID
       // TypeService typeServiceById = typeServiceService.getOneById(2);
        //System.out.println("TypeService with ID 2: " + typeServiceById);

        // Supprimer un TypeService
        //typeServiceService.supprimer(11);
// Créez un objet TypeService avec des valeurs de test

    }

    private static void assertEquals(String nouveauTypeDeService, String typeService) {
    }


}



