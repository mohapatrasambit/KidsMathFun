package org.sambit.kids.math;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.xml.stream.Space;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Utils {
    public static void generateWordDoc(List<String> equations) {
        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.LEFT);
            p1.setSpacingBetween(1);

            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setFontFamily("Verdana");
            r1.setTextPosition(100);

            for (var idx = 0; idx < equations.size(); idx++) {
                r1.setText(equations.get(idx));

                if ((idx + 1) % 3 == 0) {
                    r1.addBreak();
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
