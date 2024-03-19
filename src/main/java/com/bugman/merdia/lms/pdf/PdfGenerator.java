package com.bugman.merdia.lms.pdf;

import com.itextpdf.forms.fields.properties.CheckBoxType;
import com.itextpdf.forms.form.element.CheckBox;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.FlexContainerRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PdfGenerator {
    private PdfGenerator() {

    }

    public static void generatePurchaseOrder(String filePath) throws IOException {
        try (PdfWriter writer = new PdfWriter(new FileOutputStream(filePath))) {
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.addFont(font);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph().setMargin(0).add("Université de Constantine 3\n")
                    .add("Faculté de Médecine\n")
                    .add("Département de Pharmacie")
                    .setUnderline()
                    .setBold());
            document.add(new Paragraph("BON DE COMMANDE")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setItalic()
                    .setUnderline());
            document.add(new Paragraph("NOM: ").setBold());
            document.add(new Paragraph("Laboratoire: ").setBold());


            CheckBox checkBox = new CheckBox("checkbox")
                    .setCheckBoxType(CheckBoxType.SQUARE)
                    .setChecked(false)
                    .setSize(10)
                    .setBorder(new SolidBorder(1));

            document.add(new Paragraph()
                    .add("Produits chimiques ")
                    .add(checkBox)
                    .add(new Tab())
                    .add("Verrerie ")
                    .add(checkBox)
                    .add(new Tab())
                    .add("Matériel ")
                    .add(checkBox)
                    .add(new Tab())
                    .add("Autres ")
                    .add(checkBox)
                    .setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 5, 4, 4, 3});
            table.setWidth(UnitValue.createPercentValue(100));
            table.addHeaderCell("N°").setBold();
            table.addHeaderCell("Désignation").setBold();
            table.addHeaderCell("Quantité demandée").setBold();
            table.addHeaderCell("Quantité servie").setBold();
            table.addHeaderCell("Observation").setBold();

            for (int i = 0; i < 17; i++) {
                Table newRow = table.startNewRow();
                newRow.addCell("" + (i + 1)).setTextAlignment(TextAlignment.CENTER);
                for (int j = 0; j < 4; j++) {
                    newRow.addCell(new Cell());
                }

            }

            document.add(table);

            Div div = new Div();
            div.add(new Paragraph("Visa du responsable\n")
                    .add("Des laboratoire de pharmacie")
                    .setTextAlignment(TextAlignment.LEFT));
            div.add(new Paragraph("Responsable de labo")
                    .setTextAlignment(TextAlignment.RIGHT));
            FlexContainerRenderer renderer = new FlexContainerRenderer(div);

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            document.add(new Paragraph("Date: " + LocalDate.now().format(format))
                            .setTextAlignment(TextAlignment.RIGHT))
                    .add(new Paragraph("Visa du responsable\n")
                            .add("Des laboratoire de pharmacie")
                            .setTextAlignment(TextAlignment.LEFT))
                    .add(new Paragraph("Responsable de labo")
                            .setTextAlignment(TextAlignment.RIGHT));
            document.close();
        }
    }
}
