package pl.unity.esb.gatx.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pl.unity.esb.gatx.model.PushEvent;
import pl.unity.esb.gatx.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PushEventDAOImpl implements PushEventDAO {

    @Override
    public List<PushEvent> getAllPushEvents() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanListHandler<PushEvent> resultHandler = new BeanListHandler<>(PushEvent.class);
        return runner.query(connection, "select * from pushevent", resultHandler);
    }

    @Override
    public PushEvent getPushEventByBoxId(String boxId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanHandler<PushEvent> resultHandler = new BeanHandler<>(PushEvent.class);
        return runner.query(connection, "select * from pushevent where boxId=?", resultHandler, boxId);
    }

    @Override
    public boolean deletePushEventsByBoxId(String boxId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(connection, "DELETE from pushevent WHERE boxId=?", boxId);
        return true;
    }
}