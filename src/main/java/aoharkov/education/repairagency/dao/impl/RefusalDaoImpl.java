package aoharkov.education.repairagency.dao.impl;

import aoharkov.education.repairagency.dao.RefusalDao;
import aoharkov.education.repairagency.dao.util.HikariCPImpl;
import aoharkov.education.repairagency.entity.Refusal;
import aoharkov.education.repairagency.entity.Request;
import aoharkov.education.repairagency.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefusalDaoImpl extends AbstractCrudPageableDaoImpl<Refusal> implements RefusalDao {
    private static final String SAVE_QUERY =
            "INSERT INTO refusals (id, request_id, explanation, manager_id) values(?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM refusals WHERE id = ?";
    private static final String FIND_ALL_AT_PAGE_QUERY = "SELECT * FROM refusals LIMIT ?, ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) AS rowcount FROM refusals";
    private static final String UPDATE_QUERY =
            "UPDATE refusals SET request_id = ?, explanation = ?, manager_id = ?, WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM refusals WHERE id = ?";

    public RefusalDaoImpl(HikariCPImpl connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_AT_PAGE_QUERY, COUNT_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void fillPreparedStatementForSaveQuery(PreparedStatement preparedStatement, Refusal entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getRequest().getId());
        preparedStatement.setString(3, entity.getExplanation());
        preparedStatement.setInt(4, entity.getManager().getId());
    }

    @Override
    protected Refusal mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Refusal.builder()
                .withId(resultSet.getInt("id"))
                .withRequest(getRequestById(resultSet.getInt("request_id")))
                .withExplanation(resultSet.getString("explanation"))
                .withManager(getUserById(resultSet.getInt("manager_id")))
                .build();
    }

    @Override
    public Request getRequestById(Integer id) {
        //todo
        return null;
    }

    private User getUserById(Integer id) {
        //todo
        return null;
    }

    @Override
    protected void fillPreparedStatementForUpdateQuery(PreparedStatement preparedStatement, Refusal entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRequest().getId());
        preparedStatement.setString(2, entity.getExplanation());
        preparedStatement.setInt(3, entity.getManager().getId());
        preparedStatement.setInt(4, entity.getId());
    }

    @Override
    protected void fillPreparedStatementForDeleteByIdQuery(PreparedStatement preparedStatement, Refusal entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
    }
}
