package entities;

import java.util.Objects;

public class Services {
    private  int id ;
    private String nom_service;
    private int prix;
    private String description;
    //Relation
    TypeService typeService;
    public Services(){

    }

    public Services(int id, String nom_service, int prix, String description, TypeService typeService) {
        this.id = id;
        this.nom_service = nom_service;
        this.prix = prix;
        this.description = description;
        this.typeService = typeService;
    }

    public Services(String nom_service, int prix, String description, TypeService typeService) {
        this.nom_service = nom_service;
        this.prix = prix;
        this.description = description;
        this.typeService = typeService;
    }

    public Services(String nomService, double prix, int idTypeService, String description) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return id == services.id && Objects.equals(nom_service, services.nom_service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom_service);
    }

    @Override
    public String toString() {
        return
                "\nNom du service: " + (nom_service != null ? nom_service : "") +
                        "\nPrix: " + prix +
                        "\nDescription: " + (description != null ? description : "") +
                        "\nType de service: " + (typeService != null ? typeService.toString() : "");
    }

}
