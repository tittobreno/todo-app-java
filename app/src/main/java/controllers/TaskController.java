package controllers;

import com.google.common.util.concurrent.ExecutionError;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import models.Task;
import utils.ConnectionFactory;

public class TaskController {

    public void save(Task task) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO tasks (idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));

            LocalDateTime createdAt = task.getCreatedAt();
            statement.setTimestamp(7, Timestamp.valueOf(createdAt));

            LocalDateTime updatedAt = task.getUpdatedAt();
            statement.setTimestamp(7, Timestamp.valueOf(updatedAt));
            statement.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao salvar nova tarefa"
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Task task) {

        String sql = "UPDATE task SET"
                + "idProject = ?,"
                + "name = ?,"
                + "description = ?,"
                + "notes = ?,"
                + "completed = ?,"
                + "deadline = ?,"
                + "createdAt = ?,"
                + "updatedAt = ?."
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));

            LocalDateTime createdAt = task.getCreatedAt();
            statement.setTimestamp(7, Timestamp.valueOf(createdAt));

            LocalDateTime updatedAt = task.getUpdatedAt();
            statement.setTimestamp(7, Timestamp.valueOf(updatedAt));
            statement.execute();
  
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar tarefa" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int taskId) throws ClassNotFoundException {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Task> getAll(int idProject) {
        return null;
    }

}
