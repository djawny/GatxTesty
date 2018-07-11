package pl.unity.mule.gatx.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pl.unity.mule.gatx.ConnectionFactory;
import pl.unity.mule.gatx.model.PushData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PushDataDAOImpl implements PushDataDAO {

    @Override
    public List<PushData> getAllPushData() {

        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanListHandler<PushData> resultHandler = new BeanListHandler<>(PushData.class);
        try {
            return runner.query(connection, "select * from pushdata", resultHandler);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public PushData getPushDataByBoxId(String boxId) {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanHandler<PushData> resultHandler = new BeanHandler<>(PushData.class);
        try {
            return runner.query(connection, "select * from pushdata where boxId=?", resultHandler, boxId);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deletePushDataByBoxId(String boxId) {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        try {
            runner.update(connection, "DELETE from pushdata WHERE boxId=?", boxId);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
