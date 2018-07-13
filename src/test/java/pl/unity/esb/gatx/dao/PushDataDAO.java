package pl.unity.esb.gatx.dao;

import pl.unity.esb.gatx.model.PushData;

import java.sql.SQLException;
import java.util.List;

public interface PushDataDAO {

    List<PushData> getAllPushData() throws SQLException;

    PushData getPushDataByBoxId(String boxId) throws SQLException;

    boolean deletePushDataByBoxId(String boxId) throws SQLException;
}
