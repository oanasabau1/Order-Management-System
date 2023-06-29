package DataAccess;

import Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     *
     * @return
     */
    public String[] takeFieldNames() {
        int i = 0;
        String[] s = new String[type.getDeclaredFields().length];
        for (Field field : type.getDeclaredFields())
            s[i++] = field.getName();
        return s;
    }
    /**
     *
     * @return
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for (Field f : type.getDeclaredFields()) {
            if (!f.equals(type.getDeclaredFields()[0]))
                sb.append(f.getName() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");
        for (Field f : type.getDeclaredFields()) {
            if (!f.equals(type.getDeclaredFields()[0])) {
                if(!f.equals(type.getDeclaredFields()[1])){
                    sb.append(",");
                }
                sb.append("?");
            }

        }
        sb.append(")");
        System.out.println(sb);
        return sb.toString();
    }
    /**
     *
     * @return
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field f : type.getDeclaredFields()) {
            if (!f.equals(type.getDeclaredFields()[0])) {
                sb.append(f.getName());
                sb.append("=?, ");
            }
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(" WHERE ");
        sb.append("id = ?");
        return sb.toString();
    }
    /**
     *
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("* ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(field);
        sb.append(" = ?");
        return sb.toString();
    }
    /**
     *
     * @return
     */
    private String createSelectQueryAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("* ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }
    /**
     *
     * @param id
     */
    public void delete(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteOrdersStatement = null;
        PreparedStatement deleteStatement = null;
        try {
            dbConnection.setAutoCommit(false);
            deleteOrdersStatement = dbConnection.prepareStatement("DELETE FROM Orders WHERE clientId = ?");
            deleteOrdersStatement.setInt(1, id);
            deleteOrdersStatement.executeUpdate();
            deleteStatement = dbConnection.prepareStatement("DELETE FROM Client WHERE id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            deleteStatement = dbConnection.prepareStatement("DELETE FROM Product WHERE id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            dbConnection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:delete " + e.getMessage());
            if (dbConnection != null) {
                try {
                    dbConnection.rollback(); // Rollback the transaction
                } catch (SQLException ex) {
                    LOGGER.log(Level.WARNING, "Failed to rollback transaction: " + ex.getMessage());
                }
            }
        } finally {
            ConnectionFactory.close(deleteOrdersStatement);
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
    /**
     *
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     *
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    System.out.println(fieldName + " ");

                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     *
     * @param t
     * @return
     */
    public String[][] listOfObject(List<T> t) {
        String[][] s = new String[t.size()][t.get(0).getClass().getDeclaredFields().length];
        int i = 0, j = 0;
        for (T x : t) {
            for (Field field : x.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    s[i][j++] = String.valueOf(field.get(x));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            i++;
            j = 0;
        }
        return s;
    }
    /**
     *
     * @param t
     * @return
     */
    public T insert(T t) {
        Connection dbConnection = ConnectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement insertStatement = null;
        int i = 1;
        try {
            insertStatement = dbConnection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            for (Field f : type.getDeclaredFields()) {
                if (!f.equals(type.getDeclaredFields()[0])) {
                    f.setAccessible(true);
                    Object obj = f.get(t);
                    insertStatement.setString(i, obj.toString());
                    i++;
                }
            }
            insertStatement.executeUpdate();
            rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                Field f = type.getDeclaredField("id");
                f.setAccessible(true);
                f.set(t, id);
            }
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }
    /**
     *
     * @return
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String string = createSelectQueryAll();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(string);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:find " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     *
     * @param t
     * @param id
     * @return
     */
    public T update(T t, int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int i = 1;
        try {
            updateStatement = dbConnection.prepareStatement(createUpdateQuery(), Statement.NO_GENERATED_KEYS);
            for (Field f : type.getDeclaredFields()) {
                if (!f.equals(type.getDeclaredFields()[0])) {
                    f.setAccessible(true);
                    Object obj = f.get(t);
                    updateStatement.setString(i, obj.toString());
                    i++;
                }
            }
            updateStatement.setString(i, String.valueOf(id));
            updateStatement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }
}
