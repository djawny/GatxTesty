package pl.unity.esb.gatx.dao;

import pl.unity.esb.gatx.model.PushDataSensor;

import java.sql.SQLException;
import java.util.List;

public interface PushDataSensorDAO {

    List<PushDataSensor> getAllPushDataSensor() throws SQLException;

    PushDataSensor getPushDataSensorBySensorId(Integer sensorId) throws SQLException;

    boolean deletePushDataSensorBySensorId(Integer sensorId) throws SQLException;
}
