package utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ExcelUtil {

    //src>test>java>resources package olustur
    //excel dosyasını oraya kopyala
    //ÖNEMLİ BİLGİ : Excelde sutun yok; satırlar var ve data (hucre) lar var
    //driver Excel e gidemez; Java sahneye gelir
    //sırasıyla workbook (excel dosyası) >sayfa > satır > hucre ye ulasırız


    static public void readFromExcel () throws IOException {

        String path = "src/test/java/resources/testData.xlsx";
        FileInputStream fis = new FileInputStream(path); // ya dosyayı okuyamazsam dedi, exception attık
        Workbook workbook = WorkbookFactory.create(fis); // exception verdi, atadık

        // Biz FIS ile okudugumuz excel dosyasini projemiz icerisnde kullanabilmek icin
        // Apachi POI depenceny yardimi ile bir Workbook olusturduk

        // Bu workbook bizim projemizin icerinde ulkeler excelinin bir kopyasini kullanmamizi sagliyor

        // Excelin yapisi geregi bir hucreye(Cell) ulasabilmek icin workbookdan baslayarak
        // sirasiyla sheet, row ve cell OBJELERİ olusturmamiz gerekiyor


        Sheet sheet = workbook.getSheet("page_1");
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(1);

        System.out.println(cell);

        // indexi 1 olan satirdaki, indexi 1 olan hucrenin ozan@yahoo.com oldugunu test ediniz

        String expectedData = "ozan@yahoo.com";
        Assert.assertEquals(cell.getStringCellValue(), expectedData);

        //1.indexdeki satirin, 0 indexdeki hucre bilgisini yazdiralim
        row=sheet.getRow(1);
        cell=row.getCell(0);

        System.out.println(cell);
    }


    public static void readRowFromExcel() throws IOException {

        String path = "src/test/java/resources/testData.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);

        for (int i = 0; i < 2; i++) {

        System.out.println(workbook.getSheet("page_1").getRow(1).getCell(i).toString());
            // bu metod ile 1. indeksteki satırın 0'dan 2'ye kadarki hucre bilgilerini getirir
        }

    }
     public static void writeToExcel (String email, String password) throws IOException {

        String path = "src/test/java/resources/testData.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis); // org.apache.poi dependency ile geldi; butun Ms office dosyaları için kullanabiliriz
        // workbook a aldıgımız kopyaladıgımız orjinal excel dosyası degil bir kopyası (gölgesi) üzerinde işlem yapıyoruz;
        // o yuzden sonrasında orjinal dosyaya kaydetmemiz lazım

        workbook.getSheet("page_1").getRow(0).createCell(0).setCellValue(email);
        workbook.getSheet("page_1").getRow(0).createCell(1).setCellValue(password);

        //  kopyada yaptigimiz degisiklikleri orjinal excel dosyasına kaydedelim

        FileOutputStream fos=new FileOutputStream(path);
        workbook.write(fos);

    }


    // ---------------------------- REUSABLE METHODS -------------------------------//

    // dosya yolu,sayfa adi ve satir, hucre indexini verince hucre bilgisini dondursun (RETURN)
        public static Cell bringCellValue(String path, String sayfaAdi, int satirIndex, int hucreIndex) {
        Cell cell = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            cell = workbook.getSheet(sayfaAdi).getRow(satirIndex).getCell(hucreIndex);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cell;
    }


// dosya yolu ve sayfa ismi verilen bir excel sheet'i map olarak kaydeder
// burada excel'i oldugu gibi Map'in içine alarak istediğimiz işlemleri yapmak için bize bir excelmap dosyası RETURN eder
// bunun için de Map yapısı geregi öncelikle Excel'de hangi veriyi "Key" hangi ya da hangilerini "Value" yapacagımıza karar vermeliyiz
    public static Map<String,String> ExcelToMap(String path, String sayfaAdi) {

        Map<String,String> excelMap=new TreeMap<>(); // excelMap adında TreeMap type olarak bir object olusturduk

        Workbook workbook=null;
        //ilk adim excelde istenen sayfaya ulasmak
        try {
            FileInputStream fis =new FileInputStream(path);
            workbook=WorkbookFactory.create(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int satirSayisi=workbook.getSheet(sayfaAdi).getLastRowNum(); // Excel'deki son satırın indeksini verecek
        String key="";
        String value="";

        for (int i=0 ; i<=satirSayisi ;i++){

            // ikinci adim tablodaki hucreleri map'e uygun hale donusturmek

            // excel'in ilk sutunu key oldugu için o indeksi kullandık :
            key=workbook.getSheet(sayfaAdi).getRow(i).getCell(0).toString();

            // excel'deki 2, 3 ve 4 sutunlar value oldugu için asagıdaki sekilde (,) ile ayırarak map'a value olarak atacagız :
            value=workbook.getSheet(sayfaAdi).getRow(i).getCell(1).toString() +
                    ", " +workbook.getSheet(sayfaAdi).getRow(i).getCell(2).toString()+
                    ", " + workbook.getSheet(sayfaAdi).getRow(i).getCell(3).toString();

            // ucuncu adim key-value haline getirdigimiz satirlari map'e eklemek
            excelMap.put(key,value);
        }

        return excelMap;
    }

}
