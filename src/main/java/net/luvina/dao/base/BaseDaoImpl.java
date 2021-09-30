package net.luvina.dao.base;

import net.luvina.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BaseDaoImpl<T> implements BaseDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Connection openConnection() {
        ResourceBundle databaseResource = ResourceBundle.getBundle("database");
        String driverClassName = databaseResource.getString("driverClassName");
        String jdbcUrl = databaseResource.getString("jdbcUrl");
        String userName = databaseResource.getString("userName");
        String password = databaseResource.getString("password");
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println("BaseDaoImpl:getConnection: " + exception.getMessage());
        }
        return connection;
    }

    @Override
    public List<T> query(String sqlQuery, RowMapper rowMapper, Object[] parameters) {
        List<T> list = new ArrayList<>();
        Connection connection = openConnection();
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            setParameters(parameters);
            System.out.println(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add((T) rowMapper.rowMap(resultSet));
            }
        } catch (SQLException exception) {
            System.out.println("BaseDaoImpl:query: " + exception.getMessage());
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public int update(String sqlQuery, Object[] parameters) {
        int rows = 0;
        try {
            connection = openConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sqlQuery);
            setParameters(parameters);
            rows = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException exception1) {
                System.out.println("BaseDaoImpl:rollback: " + exception1.getMessage());
            }
            System.out.println("BaseDaoImpl:update: " + exception.getMessage());
        } finally {
            closeConnection();
        }
        return rows;
    }

    @Override
    public Long insert(String sqlQuery, Object[] parameters) {
        Long id = 0l;
        try {
            connection = openConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            setParameters(parameters);
            System.out.println(preparedStatement.toString());
            System.out.println(preparedStatement.getMetaData());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException exception1) {
                System.out.println("BaseDaoImpl:rollback: " + exception1.getMessage());
            }
            System.out.println("BaseDaoImpl:update: " + exception.getMessage());
        } finally {
            closeConnection();
        }
        return id;
    }

    @Override
    public Long count(String sqlQuery) {
        Connection connection = openConnection();
        Long total = 0l;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getLong(1);
            }
        } catch (SQLException exception) {
            System.out.println("BaseDaoImpl:count: " + exception.getMessage());
        } finally {
            closeConnection();
        }
        return total;
    }

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            System.out.println("BaseDaoImpl:closeConnection: " + exception.getMessage());
        }
    }

    private void setParameters(Object[] parameters) {

        int i = 1;
        for (Object parameter : parameters) {
            try {
                if (parameter instanceof String) {
                    preparedStatement.setString(i++, (String) parameter);
                    continue;
                }
                if (parameter instanceof Integer) {
                    preparedStatement.setInt(i++, (Integer) parameter);
                    continue;
                }
                if (parameter instanceof Long) {
                    preparedStatement.setLong(i++, (Long) parameter);
                    continue;
                }
                if (parameter instanceof Timestamp) {
                    preparedStatement.setTimestamp(i++, (Timestamp) parameter);
                    continue;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }
    }
}
