package pl.unity.mule.gatx.dao;

import pl.unity.mule.gatx.model.PushData;

import java.util.List;

public interface PushDataDAO {

    List<PushData> getAllPushData();

    PushData getPushDataByBoxId(String boxId);

    boolean deletePushDataByBoxId(String boxId);
}
