package aoharkov.training.repairagency.dao.impl;

import aoharkov.training.repairagency.dao.RepairStageDao;
import aoharkov.training.repairagency.dao.connector.Connector;
import aoharkov.training.repairagency.entity.RepairStageEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairStageDaoImpl extends AbstractCrudPageableDaoImpl<RepairStageEntity> implements RepairStageDao {
    private static final String SAVE_QUERY = "INSERT INTO repair_stages (name) values(?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM repair_stages WHERE id = ?";
    private static final String FIND_ALL_AT_PAGE_QUERY = "SELECT * FROM repair_stages LIMIT ?, ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) AS rowcount FROM repair_stages";
    private static final String UPDATE_QUERY = "UPDATE repair_stages SET name = ? WHERE id = ?";

    public RepairStageDaoImpl(Connector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_AT_PAGE_QUERY, COUNT_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    protected void fillPreparedStatementForSaveQuery(PreparedStatement preparedStatement, RepairStageEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected RepairStageEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return RepairStageEntity.builder()
                .withId(resultSet.getInt("id"))
                .withName(resultSet.getString("name"))
                .build();
    }

    @Override
    protected void fillPreparedStatementForUpdateQuery(PreparedStatement preparedStatement, RepairStageEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getId());
    }
}
