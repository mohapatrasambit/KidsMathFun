package org.sambit.kids.math.print.equations;

import lombok.Builder;
import lombok.Setter;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.sambit.kids.math.Equation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Setter
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
        switch (this.printFormat) {
            case SINGLE_LINE:
                printInSingleLineFormat(equations);
                break;
            case MULTI_LINE:
                printInMultiLineFormat(equations);
                break;
        }
    }

    private void printInMultiLineFormat(List<Equation> equations) {
        try (XWPFDocument doc = new XWPFDocument()) {

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
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printInSingleLineFormat(List<Equation> equations) {
        try (XWPFDocument doc = new XWPFDocument()) {

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
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

