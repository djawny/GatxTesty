package pl.unity.mule.gatx.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pl.unity.mule.gatx.ConnectionFactory;
import pl.unity.mule.gatx.model.PushDataSensor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PushDataSensorDAOImpl implements PushDataSensorDAO {

    @Override
    public List<PushDataSensor> getAllPushDataSensor() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanListHandler<PushDataSensor> resultHandler = new BeanListHandler<>(PushDataSensor.class);
        return runner.query(connection, "select * from pushdatasensor", resultHandler);
    }

    @Override
    public PushDataSensor getPushDataSensorBySensorId(Integer sensorId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanHandler<PushDataSensor> resultHandler = new BeanHandler<>(PushDataSensor.class);
        return runner.query(connection, "select * from pushdatasensor where sensorId=?", resultHandler, sensorId);
    }

    @Override
    public boolean deletePushDataSensorBySensorId(Integer sensorId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(connection, "DELETE from pushdatasensor WHERE sensorId=?", sensorId);
        return true;
    }
}
