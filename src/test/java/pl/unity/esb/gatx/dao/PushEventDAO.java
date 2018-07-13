package pl.unity.esb.gatx.dao;

import pl.unity.esb.gatx.model.PushEvent;

import java.sql.SQLException;
import java.util.List;

public interface PushEventDAO {

    List<PushEvent> getAllPushEvents() throws SQLException;

    PushEvent getPushEventByBoxId(String boxId) throws SQLException;

    boolean deletePushEventsByBoxId(String boxId) throws SQLException;
}
