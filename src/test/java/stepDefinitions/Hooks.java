package stepDefinitions;

import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {
    @Before("@deleteplace")
    public void beforetestcase() throws IOException {
        stepvalidation stepvalidation = new stepvalidation();

        if (stepDefinitions.stepvalidation.place_id==null) {
            stepvalidation.add_place_payload_with("youssef", "arabic", "giza");
            stepvalidation.user_calls_with_http_request("addplaceapi", "post");
            stepvalidation.verify_place_id_created_maps_to_using("youssef", "getplaceapi");
        }

    }
}
