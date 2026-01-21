package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestspecifications() throws IOException {

        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getproperties("baseurl")).setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123").build();
            return req;
        }
        return req;
    }

    public static String getproperties(String key) throws IOException {
        Properties p=new Properties();
        FileInputStream inputStream=new FileInputStream("src/test/java/resources/global.properties");
        p.load(inputStream);

        return p.getProperty(key);
    }

    public static String getresponsekey(Response response, String key){

          String rs= response.asString();
        JsonPath js=new JsonPath(rs);
        return js.get(key).toString();
    }

}
