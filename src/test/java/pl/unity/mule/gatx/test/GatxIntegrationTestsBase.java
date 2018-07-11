package pl.unity.mule.gatx.test;

import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Before;
import pl.unity.mule.gatx.Configuration;
import pl.unity.mule.gatx.HttpUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class GatxIntegrationTestsBase {

    protected final String REQUESTS_PATH = "src/test/resources/requests/";
    protected static final String SUCCESS_TRUE = "<success>true</success>";
    protected static final String SUCCESS_FALSE = "<success>false</success>";
    protected static final int HTTP_STATUS_CODE_200 = 200;
    protected static final int HTTP_STATUS_CODE_401 = 401;
    protected static final String INCORRECT_AUTHORIZATION = "";
    protected static final long TIMEOUT = 5;

    protected HttpUtil httpUtil;
    protected String authorization = Configuration.getInstance().getWebserviceAuthorization();


    @Before
    public void before() {
        httpUtil = new HttpUtil();
        Awaitility.reset();
    }

    @After
    public void after() {
        httpUtil = null;
    }

    public Path getPathToRequestXML(String xmlName) {
        return Paths.get(REQUESTS_PATH, xmlName);
    }

}
