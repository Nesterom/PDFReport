package report;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class TestController {

    public static class TestResponse {
        private ArrayList<HashMap> allEmployes = new ArrayList<>();

        public ArrayList<HashMap> getAllEmployes() {
            return allEmployes;
        }

        public void setAllEmployes(ArrayList<HashMap> param2) {
            this.allEmployes = param2;
        }
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestResponse testMethod() {
        TestResponse result = new TestResponse();
        result.setAllEmployes(EmployeesList.getList());
        return result;
    }


}
