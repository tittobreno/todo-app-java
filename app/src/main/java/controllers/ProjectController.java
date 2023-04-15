package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import models.Project;
import utils.ConnectionFactory;

public class ProjectController {

    public void save(Project project) {
        String sql = "INSERT INTO projects (name,"
                + "description,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());

            LocalDateTime createdAt = project.getCreatedAt();
            statement.setTimestamp(3, Timestamp.valueOf(createdAt));
            
            LocalDateTime updatedAt = project.getUpdatedAt();
            statement.setTimestamp(4, Timestamp.valueOf(updatedAt));
            
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar projeto: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

}
