package com.bugman.merdia.lms;

import com.bugman.merdia.lms.pdf.PdfGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class CodenameLmsApplication {

    public static void main(String[] args) throws IOException {
        // SpringApplication.run(CodenameLmsApplication.class, args);
        PdfGenerator.generatePurchaseOrder("../../testing.pdf");
    }

}
