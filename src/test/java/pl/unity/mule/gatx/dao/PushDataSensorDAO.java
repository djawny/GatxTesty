package pl.unity.mule.gatx.dao;

import pl.unity.mule.gatx.model.PushDataSensor;

import java.sql.SQLException;
import java.util.List;

public interface PushDataSensorDAO {

    List<PushDataSensor> getAllPushDataSensor() throws SQLException;

    PushDataSensor getPushDataSensorBySensorId(Integer sensorId) throws SQLException;

    boolean deletePushDataSensorBySensorId(Integer sensorId) throws SQLException;
}
