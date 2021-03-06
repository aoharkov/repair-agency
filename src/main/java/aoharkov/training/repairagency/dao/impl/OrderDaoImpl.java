package aoharkov.training.repairagency.dao.impl;

import aoharkov.training.repairagency.dao.OrderDao;
import aoharkov.training.repairagency.dao.connector.Connector;
import aoharkov.training.repairagency.entity.OrderEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderDaoImpl extends AbstractCrudPageableDaoImpl<OrderEntity> implements OrderDao {
    private static final String SAVE_QUERY =
            "INSERT INTO orders (request_id, manager_id, price, master_id, repair_stage_id) values(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String FIND_BY_REQUEST_ID_QUERY = "SELECT * FROM orders WHERE request_id = ?";
    private static final String FIND_ALL_AT_PAGE_QUERY = "SELECT * FROM orders LIMIT ?, ?";
    private static final String COUNT_ALL_QUERY = "SELECT COUNT(id) AS rowcount FROM orders";
    private static final String UPDATE_QUERY =
            "UPDATE orders SET request_id = ?, manager_id = ?, price = ?, master_id = ?, repair_stage_id = ? WHERE id = ?";

    public OrderDaoImpl(Connector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_AT_PAGE_QUERY, COUNT_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    public Optional<OrderEntity> findByRequestId(Integer requestId) {
        return findByParam(requestId, FIND_BY_REQUEST_ID_QUERY, INT_PARAM_SETTER);
    }

    @Override
    protected void fillPreparedStatementForSaveQuery(PreparedStatement preparedStatement, OrderEntity entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRequestId());
        preparedStatement.setInt(2, entity.getManagerId());
        preparedStatement.setInt(3, entity.getPrice());
        preparedStatement.setInt(4, entity.getMasterId());
        preparedStatement.setInt(5, entity.getRepairStageId());
    }

    @Override
    protected OrderEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return OrderEntity.builder()
                .withId(resultSet.getInt("id"))
                .withRequestId(resultSet.getInt("request_id"))
                .withManagerId(resultSet.getInt("manager_id"))
                .withPrice(resultSet.getInt("price"))
                .withMasterId(resultSet.getInt("master_id"))
                .withRepairStageId(resultSet.getInt("repair_stage_id"))
                .build();
    }

    @Override
    protected void fillPreparedStatementForUpdateQuery(PreparedStatement preparedStatement, OrderEntity entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRequestId());
        preparedStatement.setInt(2, entity.getManagerId());
        preparedStatement.setInt(3, entity.getPrice());
        preparedStatement.setInt(4, entity.getMasterId());
        preparedStatement.setInt(5, entity.getRepairStageId());
        preparedStatement.setInt(6, entity.getId());
    }
}
