package report;

import com.itextpdf.text.DocumentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

@RestController
public class FileController {
    static String separator = File.separator;

    public class FileResponse {
        private String fileName;
        private String fileType;
        private byte[] data;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }
    }

    @GetMapping("/file")
    public ResponseEntity<FileResponse> getFile(int id, int month, int year, @RequestParam("vocationList") ArrayList<Integer> vocationList) throws IOException, DocumentException {
        PdfReport.generateReport(id, month, year, vocationList);
        YearMonth billingYearMonth = YearMonth.of(year, month);
        Locale spanishLocal = new Locale("es", "ES");
        TextStyle style = TextStyle.FULL;
        String pathPdfFilled = "src/main/FilesRepository/PLANTILLA REGISTRO JORNADA "
                + EmployeesList.getNameSurnameById(id)
                + " "
                + billingYearMonth.getMonth().getDisplayName(style, spanishLocal)
                + ".pdf";
        File file = new File(pathPdfFilled);

        byte[] fileContent = Files.readAllBytes(file.toPath());
        String fileType = Files.probeContentType(file.toPath());

        FileResponse fileResponse = new FileResponse();
        fileResponse.setFileName(file.getName());
        fileResponse.setFileType(fileType);
        fileResponse.setData(fileContent);

        return ResponseEntity.ok(fileResponse);
    }

}
