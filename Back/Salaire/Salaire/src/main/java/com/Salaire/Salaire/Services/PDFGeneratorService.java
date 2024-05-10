package com.Salaire.Salaire.Services;

import com.Salaire.Salaire.entity.Payslip;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.colors.Color;

import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

@Service
public class PDFGeneratorService {

    Color black = new DeviceRgb(0, 0, 0);
    Color lightGray = new DeviceRgb(192, 192, 192);
    // Define colors
    Color borderColor = new DeviceRgb(221, 221, 221);
    Color backgroundColor = new DeviceRgb(221, 221, 221);
    public void generatePayslip(HttpServletResponse response, Payslip payslip) throws IOException {
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);

        PdfFont fontTitle = PdfFontFactory.createFont();

        Paragraph title = new Paragraph("FICHE DE PAIE")
                .setFont(fontTitle)
                .setFontSize(18)
                .setBold()
                .setMarginBottom(20f)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        PdfFont font = PdfFontFactory.createFont();




        Table leftTable =new Table(1);
        leftTable.setWidth(UnitValue.createPercentValue(40));

        leftTable.addCell(createCell("Nom Entreprise", String.valueOf(payslip.getNomEntreprise())));
        leftTable.addCell(createCell("Adresse", String.valueOf(payslip.getAdresseEntreprise())));
        leftTable.addCell(createCell("Affiliation CSS", String.valueOf(payslip.getAffiliationCss())));

        document.add(leftTable);

        float rightTableWidth = 200;
        float rightTableMargin = (100 - rightTableWidth) / 2;

        Table rightTable = new Table(1);
        rightTable.setWidth(UnitValue.createPercentValue(rightTableWidth));

        Date dateAjouter = payslip.getDateAjouter();
        Instant instant = dateAjouter.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        String monthName = month.getDisplayName(TextStyle.FULL, Locale.FRENCH);
        String formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);

        rightTable.addCell(createCell("Année", String.valueOf(year)));
        rightTable.addCell(createCell("Mois de Paie", monthName));
        rightTable.addCell(createCell("Date de Paiement", formattedDate));

        rightTable.setFixedPosition(358, 660, rightTableWidth); //  position
        document.add(rightTable);



        document.add(new Paragraph("\n"));


        Table table = new Table(3);
        table.setWidth(UnitValue.createPercentValue(100));

// Set border around the table
        table.setBorder(new SolidBorder(black, 1));

// Add cells for the first column
        Cell cell1 = new Cell();
        Paragraph column1Text = new Paragraph()
                .add(new Text("Matricule: ").setBold())
                .add(String.valueOf(payslip.getMatricule())).add("\n")
                .add(new Text("CIN: ").setBold())
                .add(String.valueOf(payslip.getCinEmploye())).add("\n")
                .add(new Text("N CSS: ").setBold())
                .add(String.valueOf(payslip.getNCss())).add("\n")
                .add(new Text("Situation Familiale: ").setBold())
                .add(String.valueOf(payslip.getSituationFamiliale()));
        cell1.add(column1Text);
        cell1.setBorder(new SolidBorder(black, 1));
        cell1.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell1);

// Add cells for the second column
        Cell cell2 = new Cell();
        Paragraph column2Text = new Paragraph()
                .add(new Text("Fonction\n").setBold())
                .add(String.valueOf(payslip.getFonction()))
                .setTextAlignment(TextAlignment.CENTER);

        cell2.add(column2Text);
        cell2.setBorder(new SolidBorder(black, 1));
        cell2.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell2);

// Add cells for the third column
        Cell cell3 = new Cell();
        Paragraph column3Text = new Paragraph()
                .add(new Text("Nom et Prénom\n").setBold())
                .add(payslip.getNomEmploye() )
                .add("\n")
                .add(new Text("Adresse\n").setBold())
                .add(payslip.getAdresse());
        cell3.add(column3Text);
        cell3.setBorder(new SolidBorder(black, 1));
        cell3.setTextAlignment(TextAlignment.LEFT);
        table.addCell(cell3);

        document.add(table);

        document.add(new Paragraph("\n"));

        Table table2 = new Table(new float[]{1, 1}); // Two columns
        table2.setBorder(new SolidBorder(black, 1));

        table2.setWidth(UnitValue.createPercentValue(100));
        table2.setBorder(new SolidBorder(black, 1));


        // Add table headers


        // Add table rows
        addCell(table2, "Salaire de base", font, true);
        addCell(table2, String.valueOf(payslip.getMontantMensuel()), font, false);

        addCell(table2, "Contribution sociale de solidarité", font, true);
        addCell(table2, String.valueOf(payslip.getCotisation()), font, false);

        addCell(table2, "Salaire brut imposable", font, true);
        addCell(table2, String.valueOf(payslip.getSalaireImposable()), font, false);
        addCell(table2, "Retenue à la source", font, true);
        addCell(table2, String.valueOf(payslip.getIrppMensuel()), font, false);

        addCell(table2, "Retenue CNSS", font, true);
        addCell(table2, String.valueOf(payslip.getCssMensuel()), font, false);

        addCell2(table2, "Salaire Net", font, true); // Header cell
        addCell3(table2, String.valueOf(payslip.getSalaireNet()), font, true); // Empty cell for design

        document.add(table2);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        // Add signature and date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate2 = dateFormatter.format(currentDate);
        Paragraph signature = new Paragraph("Fait à Tunis le " + formattedDate2 + "\nSignature et cachet")
                .setMarginTop(20f);
        document.add(signature);

        document.close();
    }

    private Cell createCell(String label, String value, PdfFont fontLabel, PdfFont fontValue, boolean alignLeft) {
        Paragraph p = new Paragraph();
        if (alignLeft) {
            p.add(new Text(label).setFont(fontLabel));
            p.add(new Text(value).setFont(fontValue).setTextAlignment(TextAlignment.RIGHT));
        } else {
            p.add(new Text(label).setFont(fontLabel).setTextAlignment(TextAlignment.RIGHT));
            p.add(new Text(value).setFont(fontValue));
        }
        return new Cell().add(p).setBorder(Border.NO_BORDER);
    }

    public Cell createCell(String label, String value) {
        Cell cell = new Cell();
        Paragraph paragraph = new Paragraph()
                .add(label + ":")
                .add(new Text(" " + value).setBold());
        cell.add(paragraph);

        return cell;
    }

    public void addCell2(Table table, String label, PdfFont font, boolean isHeader) {
        Cell cell = new Cell().setFont(font);

        // Set border for the cell
        cell.setBorder(Border.NO_BORDER);

        if (isHeader) {
            cell.add(new Paragraph(label).setBold());
            cell.setBackgroundColor(lightGray);
        } else {
            cell.add(new Paragraph(label + ": " ));
        }

        table.addCell(cell);
    }
    public void addCell3(Table table, String value, PdfFont font, boolean isHeader) {
        Cell cell = new Cell().setFont(font);

        // Set border for the cell
        cell.setBorder(Border.NO_BORDER);

        if (isHeader) {
            cell.add(new Paragraph(value).setBold());
            cell.setBackgroundColor(lightGray);
        } else {
            cell.add(new Paragraph(value ));
        }

        table.addCell(cell);
    }
    public void addCell(Table table, String text, PdfFont font, boolean isHeader) {
        Cell cell = new Cell();
        Paragraph paragraph = new Paragraph(text).setFont(font);
        cell.add(paragraph);
        cell.setBorder(new SolidBorder(black, 1));

        // Set font-family
        cell.setFontSize(12);
        cell.setFontColor(black);
        cell.setFont(FontConstants.COURIER_OBLIQUE);

        // Set border
        cell.setBorder(new SolidBorder(borderColor, 1));

        // Set text alignment
        cell.setTextAlignment(TextAlignment.LEFT);

        cell.setPadding(8);

        // Set background color
        if (isHeader) {
            cell.setBackgroundColor(borderColor);
        }


        table.addCell(cell);
    }

}
