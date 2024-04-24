package services;

import entities.Services;
import entities.TypeService;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicesService implements IService<Services> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Services p) throws SQLException {
        String query = "INSERT INTO services (nom_service, prix, description, relation_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, p.getNom_service());
            preparedStatement.setDouble(2, p.getPrix());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setInt(4, p.getTypeService().getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifier(Services p) throws SQLException {
        String query = "UPDATE services SET nom_service=?, prix=?, description=?, relation_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, p.getNom_service());
            preparedStatement.setDouble(2, p.getPrix());
            preparedStatement.setString(3, p.getDescription());
            preparedStatement.setInt(4, p.getTypeService().getId());
            preparedStatement.setInt(5, p.getId());
            preparedStatement.executeUpdate();
        }
    }


    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM services WHERE id=?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void supprimerTypeService(int id) throws SQLException {

    }

    @Override
    public Services getOneById(int id) throws SQLException {
        String query = "SELECT * FROM services WHERE id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Services service = new Services();
                    service.setId(rs.getInt("id"));
                    service.setNom_service(rs.getString("nom_service"));
                    service.setDescription(rs.getString("description"));

                    TypeService type = new TypeService();
                    type.setId(rs.getInt("relation_id"));

                    service.setTypeService(type);

                    return service;
                }
            }
        }
        return null; // Return null if no service with the given id is found
    }
    @Override
    public List<Services> getAll() throws SQLException {
        List<Services> services = new ArrayList<>();
        String query = "SELECT * FROM services";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Services service = new Services();
                service.setId(rs.getInt("id"));
                service.setNom_service(rs.getString("nom_service"));
                service.setDescription(rs.getString("description"));

                TypeService type = new TypeService();
                type.setId(rs.getInt("relation_id"));

                service.setTypeService(type);

                services.add(service);
            }
        }

        return services;
    }


}
