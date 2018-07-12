package pl.unity.mule.gatx.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pl.unity.mule.gatx.ConnectionFactory;
import pl.unity.mule.gatx.model.PushEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PushEventDAOImpl implements PushEventDAO {

    @Override
    public List<PushEvent> getAllPushEvents() {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanListHandler<PushEvent> resultHandler = new BeanListHandler<>(PushEvent.class);
        try {
            return runner.query(connection, "select * from pushevent", resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public PushEvent getPushEventByBoxId(String boxId) {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanHandler<PushEvent> resultHandler = new BeanHandler<>(PushEvent.class);
        try {
            return runner.query(connection, "select * from pushevent where boxId=?", resultHandler, boxId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deletePushEventsByBoxId(String boxId) {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        try {
            runner.update(connection, "DELETE from pushevent WHERE boxId=?", boxId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}