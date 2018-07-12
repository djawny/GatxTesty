package pl.unity.mule.gatx.dao;

import pl.unity.mule.gatx.model.PushEvent;

import java.sql.SQLException;
import java.util.List;

public interface PushEventDAO {

    List<PushEvent> getAllPushEvents() throws SQLException;

    PushEvent getPushEventByBoxId(String boxId) throws SQLException;

    boolean deletePushEventsByBoxId(String boxId) throws SQLException;
}
