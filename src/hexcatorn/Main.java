package hexcatorn;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import jxl.write.*;
import jxl.write.Number;
import jxl.format.Colour;
import jxl.Workbook;
import jxl.WorkbookSettings;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{
        //WriteToTextFile();
        odczyt();
    }
    private static void WriteToTextFile() throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("C:\\Users\\Hexatorn\\Desktop\\ala.txt");
        zapis.println("Ala ma kota, a kot ma Alę");
        zapis.close();
    }

    private static void odczyt () throws FileNotFoundException{
        File file = new File("C:\\Users\\Hexatorn\\Desktop\\ala.txt");
        Scanner in = new Scanner(file);

        String zdanie = in.nextLine();
        System.out.println(zdanie);
        in.nextLine();
        System.out.println(zdanie);
    }

    private static void ReadFromExcel() throws IOException, BiffException {
        Workbook skoroszyt = Workbook.getWorkbook(new File("przyklad2.xls"));
        Sheet arkusz = skoroszyt.getSheet(0);
        Cell komorka = arkusz.getCell(1, 0);
        String naglowek = komorka.getContents();
        System.out.println(naglowek);
        for(int i=1; i<4; i++){
            komorka = arkusz.getCell(1, i);
            System.out.println(komorka.getContents());
        }
        skoroszyt.close();
        System.out.println("Odczyt zakończony sukcesem.");
    }

    private static void WriteToExcel() throws IOException, WriteException {
        WritableWorkbook skoroszyt = Workbook.createWorkbook(new File("przyklad2.xls"));
        WritableSheet arkusz = skoroszyt.createSheet("Arkusz", 0);

        WritableFont font = new WritableFont(WritableFont.TAHOMA, 12);

        WritableCellFormat tahoma = new WritableCellFormat(font);
        tahoma.setBackground(Colour.LIGHT_BLUE);

        Label tekst = new Label(1, 0, "Liczby", tahoma);
        arkusz.addCell(tekst);

        WritableCellFormat format = new WritableCellFormat(NumberFormats.FLOAT);
        Number number = new Number(1, 1, 5.1, format);
        arkusz.addCell(number);
        number = new Number(1, 2, 2.6754, format);
        arkusz.addCell(number);
        number = new Number(1, 3, 6.75, format);
        arkusz.addCell(number);

        Formula formula = new Formula(1, 4, "SUM(B2:B4)", tahoma);
        arkusz.addCell(formula);

        WritableCellFormat formatDaty = new WritableCellFormat(new DateFormat("dd-MM-yyyy"));
        DateTime data = new DateTime(1, 6, Calendar.getInstance().getTime(), formatDaty);
        arkusz.addCell(data);

        skoroszyt.write();
        skoroszyt.close();
        System.out.println("Dokument został wygenerowany.");
    }

}
