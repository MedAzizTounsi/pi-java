package services;

import entities.TypeService;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeServiceService implements IService<TypeService> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(TypeService typeService) throws SQLException {
        String query = "INSERT INTO type_service (type_service, discription) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, typeService.getType_service());
            preparedStatement.setString(2, typeService.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifier(TypeService p) throws SQLException {
        String query = "UPDATE type_service SET type_service=?, discription=? WHERE id=?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, p.getType_service());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setInt(3, p.getId());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM type_service WHERE id=?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void supprimerTypeService(int id) throws SQLException {

    }


    @Override
    public TypeService getOneById(int id) throws SQLException {

        String query = "SELECT * FROM type_service WHERE id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    TypeService typeService = new TypeService();
                    typeService.setId(rs.getInt("id"));
                    typeService.setTypeService(rs.getString("type_service"));
                    typeService.setDescription(rs.getString("discription"));
                    // Définissez d'autres propriétés de TypeService si nécessaire

                    return typeService;
                }
            }
        }
        return null; // Retourne null si aucun TypeService avec l'ID donné n'est trouvé
    }

    @Override
    public List<TypeService> getAll() throws SQLException {
        List<TypeService> typeServices = new ArrayList<>();
        String query = "SELECT * FROM type_service";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                TypeService typeService = new TypeService();
                typeService.setId(rs.getInt("id"));
                typeService.setTypeService(rs.getString("type_service"));
                typeService.setDescription(rs.getString("discription"));

                typeServices.add(typeService);
            }
        }

        return typeServices;
    }

    public int getIdTypeService(String typeService) throws SQLException {
        String query = "SELECT id FROM type_service WHERE type_service = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, typeService);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        throw new SQLException("Type de service non trouvé : " + typeService);

    }
    public List<TypeService> getTypeServicesOfService(int serviceId) throws SQLException {
        List<TypeService> typeServices = new ArrayList<>();
        String query = "SELECT ts.* FROM type_service ts " +
                "JOIN services s ON ts.id = s.idtype " +
                "WHERE s.id = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, serviceId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    TypeService typeService = new TypeService();
                    typeService.setId(rs.getInt("id"));
                    typeService.setTypeService(rs.getString("type_service"));
                    typeService.setDescription(rs.getString("discription"));

                    typeServices.add(typeService);
                }
            }
        }

        return typeServices;
    }

}
