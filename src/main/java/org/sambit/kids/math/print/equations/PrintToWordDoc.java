package org.sambit.kids.math.print.equations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.sambit.kids.math.Equation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Setter
@AllArgsConstructor
public class PrintToWordDoc implements PrintEquations{
    @Builder.Default
    private WordDocPrintFormat printFormat = WordDocPrintFormat.SINGLE_LINE;

    /**
     * Prints equations to word doc
     *
     * @param equations
     */
    @Override
    public void print(List<Equation> equations) {
        var doc = createDocumentWithHeader();

        switch (this.printFormat) {
            case SINGLE_LINE:
                printInSingleLineFormat(equations, doc);
                break;
            case MULTI_LINE:
                printInMultiLineFormat(equations, doc);
                break;
        }
    }

    private XWPFDocument createDocumentWithHeader() {
            XWPFDocument doc = new XWPFDocument();
            XWPFTable tableNameAndTime = doc.createTable();
            tableNameAndTime.setWidth("96%");
            tableNameAndTime.setCellMargins(0, 300, 0, 300);

            var nameRow = tableNameAndTime.getRow(0);
            var nameLabelCell = nameRow.getCell(0);
            nameLabelCell.setWidth("24%");
            nameLabelCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            nameRow.setHeight((int)(600)); //Height is set with twipsPerInch, which is 1440 per inch.
            nameRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT); //set w:hRule="exact"
            nameLabelCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
            var nameLabelCellRun = createRun(nameLabelCell.getParagraphs().get(0), 12);
            nameLabelCellRun.setText("Name: ");

            var nameEntryCell = nameRow.createCell();
            nameEntryCell.setWidth("72%");;
            nameEntryCell.getCTTc().getTcPr().addNewGridSpan();
            nameEntryCell.getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long)3));

            var timeRow = tableNameAndTime.createRow();
            timeRow.setHeight((int)(600)); //Height is set with twipsPerInch, which is 1440 per inch.
            timeRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT); //set w:hRule="exact"

            var startTimeLabelCell = timeRow.getCell(0);
            startTimeLabelCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            startTimeLabelCell.setWidth("24%");
            startTimeLabelCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);

            var startTimeCellRun = createRun(startTimeLabelCell.getParagraphs().get(0), 12);
            startTimeCellRun.setText("Start time: ");

            timeRow.getCell(1).setWidth("24%"); // Start time entry cell

            var endTimeCell = timeRow.createCell();
            endTimeCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            endTimeCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
            endTimeCell.setWidth("24%");

            var endTimeCellRun = createRun(endTimeCell.getParagraphs().get(0), 12);
            endTimeCellRun.setText("End time: ");

            // End time entry cell
            timeRow.createCell();
            timeRow.getCell(3).setWidth("24%");

            return doc;
    }

    private void printInMultiLineFormat(List<Equation> equations, XWPFDocument doc) {
        XWPFTable table = null;
        XWPFTableRow rowX = null;
        XWPFTableRow rowY = null;
        XWPFTableRow rowZ = null;
        XWPFTableRow answerRow = null;

        for (Integer idx = 0; idx < equations.size(); idx++) {
            Integer colIdx = idx % 3;

            if (idx % 3 == 0) {
                table = doc.createTable();
                table.setWidthType(TableWidthType.PCT);
                table.setWidth("90%");
                table.removeBorders();

                table.getRow(0).getCell(0).setWidth("32");
                table.getRow(0).addNewTableCell().setWidth("32");
                table.getRow(0).addNewTableCell().setWidth("32");

                rowX = table.createRow();
                rowY = table.createRow();
                rowZ = table.createRow();

                answerRow = table.createRow(); // answer row
                createRun(answerRow.getCell(colIdx).getParagraphs().get(0)).setText("  ");
                answerRow.setHeight((int)(900)); //Height is set with twipsPerInch, which is 1440 per inch.
                answerRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT); //set w:hRule="exact"

            }

            rowX.getCell(colIdx).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
            rowY.getCell(colIdx).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);
            rowZ.getCell(colIdx).getParagraphs().get(0).setAlignment(ParagraphAlignment.RIGHT);

            rowX.getCell(colIdx).getParagraphs().get(0).getCTP().addNewPPr().addNewKeepLines().setVal(STOnOff.ON);
            rowX.getCell(colIdx).getParagraphs().get(0).getCTP().getPPr().addNewKeepNext().setVal(STOnOff.ON);
            rowY.getCell(colIdx).getParagraphs().get(0).getCTP().addNewPPr().addNewKeepLines().setVal(STOnOff.ON);
            rowY.getCell(colIdx).getParagraphs().get(0).getCTP().getPPr().addNewKeepNext().setVal(STOnOff.ON);
            rowZ.getCell(colIdx).getParagraphs().get(0).getCTP().addNewPPr().addNewKeepLines().setVal(STOnOff.ON);
            rowZ.getCell(colIdx).getParagraphs().get(0).getCTP().getPPr().addNewKeepNext().setVal(STOnOff.ON);

            createRun(rowX.getCell(colIdx).getParagraphs().get(0))
                    .setText("   " + equations.get(idx).getLeft().toString());

            createRun(rowY.getCell(colIdx).getParagraphs().get(0))
                    .setText(" " + equations.get(idx).getOperator().toString() + " " +
                            equations.get(idx).getRight().toString());
            rowZ.getCell(idx % 3).setText("_________");
        }

        String fileName = Timestamp.from(Instant.now()).toString() + ".docx";
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            doc.write(out);
            doc.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static XWPFRun createRun(XWPFParagraph paragraph){
        return createRun(paragraph, 19);
    }

    public static XWPFRun createRun(XWPFParagraph paragraph, Integer fontSize){
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(fontSize);
        run.setFontFamily("Verdana");
        return run;
    }

    private void printInSingleLineFormat(List<Equation> equations, XWPFDocument doc) {
        XWPFParagraph p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.LEFT);
        p1.setSpacingBetween(1);

        XWPFRun r1 = p1.createRun();
        r1.setBold(true);
        r1.setFontFamily("Verdana");
        r1.setTextPosition(100);

        for (var idx = 0; idx < equations.size(); idx++) {
            var equation = equations.get(idx);
            r1.setText(equation.getLeft().toString() + " ");
            r1.setText(equation.getOperator() + " ");
            r1.setText(equation.getRight().toString() + " = ");

            if ((idx + 1) % 3 == 0) {
                r1.addBreak();
            } else {
                r1.setText("\t\t\t");
            }
        }

        String fileName = Timestamp.from(Instant.now()).toString() + ".docx";
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            doc.write(out);
            doc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

