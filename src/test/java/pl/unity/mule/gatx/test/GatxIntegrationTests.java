package pl.unity.mule.gatx.test;

import org.apache.http.HttpResponse;
import org.junit.Test;
import pl.unity.mule.gatx.DataFormattingUtil;
import pl.unity.mule.gatx.dao.*;
import pl.unity.mule.gatx.model.PushData;
import pl.unity.mule.gatx.model.PushDataSensor;
import pl.unity.mule.gatx.model.PushEvent;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


public class GatxIntegrationTests extends GatxIntegrationTestsBase {

    private DataFormattingUtil formattingUtil = new DataFormattingUtil("yyyy-MM-dd HH:mm:ss.SSS");
    private PushEventDAO pushEventDAO = new PushEventDAOImpl();
    private PushDataDAO pushDataDAO = new PushDataDAOImpl();
    private PushDataSensorDAO pushDataSensorDAO = new PushDataSensorDAOImpl();

    @Test
    public void shouldReturnSuccessTrueForPushEventRequestAndStoreEventInDatabaseCorrectly() throws Exception {
        //given
        List<PushEvent> events = createEvents();
        Path pathToFile = getPathToRequestXML("PushEventArray.xml");
        for (PushEvent event : events) {
            await().atMost(TIMEOUT, SECONDS).until(() -> pushEventDAO.deletePushEventsByBoxId(event.getBoxId()));
        }

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_TRUE);
        for (PushEvent event : events) {
            await().atMost(TIMEOUT, SECONDS).untilAsserted(() -> assertThat(pushEventDAO.getPushEventByBoxId(event.getBoxId())).isEqualToIgnoringNullFields(event));
        }
    }

    @Test
    public void shouldReturnSuccessTrueForPushEventRequestWhenExtraFields() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushEventArrayExtraFields.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_TRUE);
    }

    @Test
    public void shouldReturnSuccessFalseForPushEventRequestWhenIncorrectEnumValue() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushEventArrayIncorrectEnumValue.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_FALSE);
    }

    @Test
    public void shouldReturnSuccessFalseForPushEventRequestWhenIncorrectDateFormat() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushEventArrayIncorrectDateFormat.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_FALSE);
    }

    @Test
    public void shouldReturnHttpStatusCode401ForPushEventRequestWhenIncorrectAuthorization() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushEventArray.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, INCORRECT_AUTHORIZATION);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_401);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).isEmpty();
    }

    @Test
    public void shouldReturnSuccessTrueForPushDataRequestAndStoreDataInDatabaseCorrectly() throws Exception {
        //given
        List<PushData> pushData = createPushData();
        List<PushDataSensor> pushDataSensors = createPushDataSensors();
        Path pathToFile = getPathToRequestXML("PushDataArray.xml");
        for (PushData data : pushData) {
            await().atMost(TIMEOUT, SECONDS).until(() -> pushDataDAO.deletePushDataByBoxId(data.getBoxId()));
        }
        for (PushDataSensor pushDataSensor : pushDataSensors) {
            await().atMost(TIMEOUT, SECONDS).until(() -> pushDataSensorDAO.deletePushDataSensorBySensorId(pushDataSensor.getSensorId()));
        }

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_TRUE);
        for (PushData data : pushData) {
            await().atMost(TIMEOUT, SECONDS).untilAsserted(() -> assertThat(pushDataDAO.getPushDataByBoxId(data.getBoxId())).isEqualToIgnoringNullFields(data));
        }
        for (PushDataSensor pushDataSensor : pushDataSensors) {
            await().atMost(TIMEOUT, SECONDS).untilAsserted(() -> assertThat(pushDataSensorDAO.getPushDataSensorBySensorId(pushDataSensor.getSensorId())).isEqualToIgnoringNullFields(pushDataSensor));
        }
    }

    @Test
    public void shouldReturnSuccessTrueForPushDataRequestWhenExtraFields() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushDataArrayExtraFields.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_TRUE);
    }

    @Test
    public void shouldReturnSuccessFalseForPushDataRequestWhenIncorrectEnumValue() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushDataArrayIncorrectEnumValue.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_FALSE);
    }

    @Test
    public void shouldReturnSuccessFalseForPushDataRequestWhenIncorrectDateFormat() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushDataArrayIncorrectDateFormat.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_FALSE);
    }

    @Test
    public void shouldReturnSuccessFalseForPushDataRequestWhenIncorrectDataType() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushDataArrayIncorrectDataType.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, authorization);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_200);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).contains(SUCCESS_FALSE);
    }

    @Test
    public void shouldReturnHttpStatusCode401ForPushDataRequestWhenIncorrectAuthorization() throws Exception {
        //given
        Path pathToFile = getPathToRequestXML("PushDataArray.xml");

        //when
        HttpResponse response = httpUtil.sendPost(pathToFile, INCORRECT_AUTHORIZATION);

        //then
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HTTP_STATUS_CODE_401);
        String readFromResponseBody = httpUtil.readFromResponseBody(response);
        assertThat(readFromResponseBody).isEmpty();
    }

    private List<PushEvent> createEvents() {

        PushEvent pushEvent1 = new PushEvent();
        pushEvent1.setAssetId("4711");
        pushEvent1.setAssetName("3780 4993 602-1");
        pushEvent1.setBoxId("1111");
        pushEvent1.setEventTimestamp(formattingUtil.makeTimestampFromString("2017-04-05 10:10:23.500"));
        pushEvent1.setEventType("O");
        pushEvent1.setValue("Kempten1");
        pushEvent1.setStatus("A");
        pushEvent1.setInsertuser("TEST");
        pushEvent1.setUpdateuser("TEST");

        PushEvent pushEvent2 = new PushEvent();
        pushEvent2.setAssetId("4711");
        pushEvent2.setAssetName("3780 4993 602-1");
        pushEvent2.setBoxId("1112");
        pushEvent2.setEventTimestamp(formattingUtil.makeTimestampFromString("2017-04-06 10:10:23.500"));
        pushEvent2.setEventType("I");
        pushEvent2.setValue("Kempten2");
        pushEvent2.setStatus("A");
        pushEvent2.setInsertuser("TEST");
        pushEvent2.setUpdateuser("TEST");

        PushEvent pushEvent3 = new PushEvent();
        pushEvent3.setAssetId("4712");
        pushEvent3.setAssetName("3780 4993 602-2");
        pushEvent3.setBoxId("1113");
        pushEvent3.setEventTimestamp(formattingUtil.makeTimestampFromString("2017-04-07 10:10:23.500"));
        pushEvent3.setEventType("O");
        pushEvent3.setValue("Kempten3");
        pushEvent3.setStatus("A");
        pushEvent3.setInsertuser("TEST");
        pushEvent3.setUpdateuser("TEST");

        return Arrays.asList(pushEvent1, pushEvent2, pushEvent3);
    }

    private List<PushData> createPushData() {

        PushData pushData1 = new PushData();
        pushData1.setAssetId(4711);
        pushData1.setAssetIdProvider("d123456789");
        pushData1.setAssetName("3780 4993 602-1");
        pushData1.setBoxId("vh47112561");
        pushData1.setTimestamp(formattingUtil.makeTimestampFromString("2016-08-20 08:11:30.500"));
        pushData1.setTimeReceived(formattingUtil.makeTimestampFromString("2016-08-21 08:11:30.500"));
        pushData1.setMoveCount(400);
        pushData1.setStopCount(399);
        pushData1.setMoveState("D");
        pushData1.setVoltage(12.40);
        pushData1.setEnergyConsumption(1.20);
        pushData1.setRemainingBatteryCapacity(85);
        pushData1.setDerailed("N");
        pushData1.setLatitude(13.123456);
        pushData1.setLongitude(10.123456);
        pushData1.setHeight(168.00);
        pushData1.setDirection(15);
        pushData1.setHeading(15);
        pushData1.setTotalDistance(1532654.2);
        pushData1.setSpeed(32.5);
        pushData1.setSatellites(7);
        pushData1.setStreet("An der Stiftsbleiche");
        pushData1.setStreetNumber("11");
        pushData1.setZipCode("87439");
        pushData1.setCity("Kempten");
        pushData1.setCountry("Deutschland");
        pushData1.setIsoCountryCode("DE");
        pushData1.setLoaded("Y");
        pushData1.setOverloaded("Y");
        pushData1.setWeighing(12000);
        pushData1.setShockAmplitudeX(6.6);
        pushData1.setShockDurationX(1);
        pushData1.setShockAmplitudeY(4.6);
        pushData1.setShockDurationY(0);
        pushData1.setShockAmplitudeZ(5.600);
        pushData1.setShockDurationZ(2);
        pushData1.setStatus("A");
        pushData1.setInsertuser("TEST");
        pushData1.setUpdateuser("TEST");

        PushData pushData2 = new PushData();
        pushData2.setAssetId(4712);
        pushData2.setAssetIdProvider("987654321");
        pushData2.setAssetName("3780 4993 602-2");
        pushData2.setBoxId("vh47112562");
        pushData2.setTimestamp(formattingUtil.makeTimestampFromString("2016-07-20 08:11:30.500"));
        pushData2.setTimeReceived(formattingUtil.makeTimestampFromString("2016-07-21 08:11:30.500"));
        pushData2.setMoveCount(500);
        pushData2.setStopCount(499);
        pushData2.setMoveState("S");
        pushData2.setVoltage(12.40);
        pushData2.setEnergyConsumption(1.20);
        pushData2.setRemainingBatteryCapacity(85);
        pushData2.setDerailed("Y");
        pushData2.setLatitude(13.123456);
        pushData2.setLongitude(10.123456);
        pushData2.setHeight(168.00);
        pushData2.setDirection(15);
        pushData2.setHeading(15);
        pushData2.setTotalDistance(1532654.11);
        pushData2.setSpeed(32.5);
        pushData2.setSatellites(7);
        pushData2.setStreet("An der Stiftsbleiche");
        pushData2.setStreetNumber("11");
        pushData2.setZipCode("87439");
        pushData2.setCity("Kempten");
        pushData2.setCountry("Deutschland");
        pushData2.setIsoCountryCode("DE");
        pushData2.setLoaded("Y");
        pushData2.setOverloaded("N");
        pushData2.setWeighing(10000);
        pushData2.setShockAmplitudeX(6.6);
        pushData2.setShockDurationX(1);
        pushData2.setShockAmplitudeY(4.6);
        pushData2.setShockDurationY(0);
        pushData2.setShockAmplitudeZ(5.600);
        pushData2.setShockDurationZ(2);
        pushData2.setStatus("A");
        pushData2.setInsertuser("TEST");
        pushData2.setUpdateuser("TEST");

        PushData pushData3 = new PushData();
        pushData3.setAssetId(4712);
        pushData3.setAssetIdProvider("987654321");
        pushData3.setAssetName("3780 4993 602-2");
        pushData3.setBoxId("vh47112563");
        pushData3.setTimestamp(formattingUtil.makeTimestampFromString("2016-06-20 08:11:30.500"));
        pushData3.setTimeReceived(formattingUtil.makeTimestampFromString("2016-06-21 08:11:30.500"));
        pushData3.setMoveCount(500);
        pushData3.setStopCount(499);
        pushData3.setMoveState("P");
        pushData3.setVoltage(12.40);
        pushData3.setEnergyConsumption(1.20);
        pushData3.setRemainingBatteryCapacity(85);
        pushData3.setDerailed("N");
        pushData3.setLatitude(13.123456);
        pushData3.setLongitude(10.123456);
        pushData3.setHeight(168.00);
        pushData3.setDirection(15);
        pushData3.setHeading(15);
        pushData3.setTotalDistance(1532654.11);
        pushData3.setSpeed(32.5);
        pushData3.setSatellites(7);
        pushData3.setStreet("An der Stiftsbleiche");
        pushData3.setStreetNumber("11");
        pushData3.setZipCode("87439");
        pushData3.setCity("Kempten");
        pushData3.setCountry("Deutschland");
        pushData3.setIsoCountryCode("DE");
        pushData3.setLoaded("N");
        pushData3.setOverloaded("N");
        pushData3.setWeighing(10000);
        pushData3.setShockAmplitudeX(6.6);
        pushData3.setShockDurationX(1);
        pushData3.setShockAmplitudeY(4.6);
        pushData3.setShockDurationY(0);
        pushData3.setShockAmplitudeZ(5.600);
        pushData3.setShockDurationZ(2);
        pushData3.setStatus("A");
        pushData3.setInsertuser("TEST");
        pushData3.setUpdateuser("TEST");

        return Arrays.asList(pushData1, pushData2, pushData3);
    }

    private List<PushDataSensor> createPushDataSensors() {

        PushDataSensor pushDataSensor1 = new PushDataSensor();
        pushDataSensor1.setSensorId(1212);
        pushDataSensor1.setSensorDescription("Project Z Testsensor 1");
        pushDataSensor1.setSensorType("T");
        pushDataSensor1.setValue(14.33);

        PushDataSensor pushDataSensor2 = new PushDataSensor();
        pushDataSensor2.setSensorId(1313);
        pushDataSensor2.setSensorDescription("Project Z Testsensor 2");
        pushDataSensor2.setSensorType("H");
        pushDataSensor2.setValue(12.12);

        PushDataSensor pushDataSensor3 = new PushDataSensor();
        pushDataSensor3.setSensorId(1414);
        pushDataSensor3.setSensorDescription("Project Z Testsensor 3");
        pushDataSensor3.setSensorType("P");
        pushDataSensor3.setValue(14.33);

        PushDataSensor pushDataSensor4 = new PushDataSensor();
        pushDataSensor4.setSensorId(1515);
        pushDataSensor4.setSensorDescription("Project Z Testsensor 4");
        pushDataSensor4.setSensorType("T");
        pushDataSensor4.setValue(14.33);

        return Arrays.asList(pushDataSensor1, pushDataSensor2, pushDataSensor3, pushDataSensor4);
    }
}
