package pl.unity.mule.gatx.model;

public class PushDataSensor {

    private Long datasensorid;
    private Integer dataid;
    private Integer sensorId;
    private String sensorDescription;
    private String sensorType;
    private Double value;

    public Long getDatasensorid() {
        return datasensorid;
    }

    public void setDatasensorid(Long datasensorid) {
        this.datasensorid = datasensorid;
    }

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
