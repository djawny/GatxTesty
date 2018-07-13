package pl.unity.esb.gatx.test;

import org.awaitility.Awaitility;
import org.junit.After;
import org.junit.Before;
import pl.unity.esb.gatx.Configuration;
import pl.unity.esb.gatx.HttpUtil;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class GatxIntegrationTestsBase {

    protected final String REQUESTS_PATH = "src/test/resources/requests/";
    protected static final String SUCCESS_TRUE = "<success>true</success>";
    protected static final String SUCCESS_FALSE = "<success>false</success>";
    protected static final int HTTP_STATUS_CODE_200 = 200;
    protected static final int HTTP_STATUS_CODE_401 = 401;
    protected static final String INCORRECT_AUTHORIZATION = "Basic 12345";
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
