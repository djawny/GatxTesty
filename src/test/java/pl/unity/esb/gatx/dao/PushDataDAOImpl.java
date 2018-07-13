package pl.unity.esb.gatx.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pl.unity.esb.gatx.ConnectionFactory;
import pl.unity.esb.gatx.model.PushData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PushDataDAOImpl implements PushDataDAO {

    @Override
    public List<PushData> getAllPushData() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanListHandler<PushData> resultHandler = new BeanListHandler<>(PushData.class);
        return runner.query(connection, "select * from pushdata", resultHandler);
    }

    @Override
    public PushData getPushDataByBoxId(String boxId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanHandler<PushData> resultHandler = new BeanHandler<>(PushData.class);
        return runner.query(connection, "select * from pushdata where boxId=?", resultHandler, boxId);
    }

    @Override
    public boolean deletePushDataByBoxId(String boxId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(connection, "DELETE from pushdata WHERE boxId=?", boxId);
        return true;
    }
}
