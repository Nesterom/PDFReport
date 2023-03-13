package report;

import com.itextpdf.text.DocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
@SpringBootApplication
public class Test {
    public static void main(String[] args) throws IOException, InvalidFormatException, DocumentException {
              SpringApplication.run(Test.class);



     /*   ArrayList<Integer> listOfVocations = new ArrayList<>();
*//*        for (int i = 27; i < 31; i++){
            listOfVocations.add(i);
        }*//*


    PdfReport.generateReport(0,1,2023,listOfVocations);*/
    }
}




