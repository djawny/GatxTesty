package pl.unity.mule.gatx.dao;

import pl.unity.mule.gatx.model.PushEvent;

import java.util.List;

public interface PushEventDAO {

    List<PushEvent> getAllPushEvents();

    PushEvent getPushEventByBoxId(String boxId);

    boolean deletePushEventsByBoxId(String boxId);
}
