package pl.unity.mule.gatx.dao;

import pl.unity.mule.gatx.model.PushDataSensor;

import java.util.List;

public interface PushDataSensorDAO {

    List<PushDataSensor> getAllPushDataSensor();

    PushDataSensor getPushDataSensorBySensorId(Integer sensorId);

    boolean deletePushDataSensorBySensorId(Integer sensorId);
}
