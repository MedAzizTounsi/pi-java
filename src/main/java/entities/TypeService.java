package entities;

import java.util.Objects;

public class TypeService {
    private  int id ;
    private String type_service;
    private String discription;

    public TypeService(String type_service, String description) {
        this.type_service = type_service;
        this.discription = description;
    }

    public TypeService(int id) {
        this.id = id;
    }

    public TypeService() {}


    public TypeService(int i, String typeUpdated, String descriptionUpdated) {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_service() {
        return type_service;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

    public String getDescription() {
        return discription;
    }

    public void setDescription(String description) {
        this.discription = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeService that = (TypeService) o;
        return id == that.id && Objects.equals(type_service, that.type_service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type_service);
    }

    @Override
    public String toString() {
        return " type_service='" + getType_service() + '\'' +
                "\n  description='" + getDescription() + '\'' ;
    }

    public void setTypeService(String typeService) {
    }
}
