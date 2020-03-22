package com.example.shoppingapplication.gmailSender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PDFSaver {


    public void toExternalStorage(Context context, String content ){
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            System.out.println("NO EXTERNAL STORAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        }
        else {
            // create a new document
            PdfDocument document = new PdfDocument();
            // crate a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            int x =80, y = 60;

            for(String s: content.split("\n")) {
                canvas.drawText(s, x, y, paint);
               y += -paint.ascent() + paint.descent();
            }
            // finish the page
            document.finishPage(page);

            // write the document content
//            String directory_path = Environment.getExternalStorageDirectory().getPath() + "/pdftmp/";
//            File file = new File(directory_path);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            String targetPdf = directory_path+"temp.pdf";
//            File filePath = new File(targetPdf);
//
//            try {
//                document.writeTo(new FileOutputStream(filePath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // close the document
            File dir = new File(Environment.getExternalStorageDirectory().getPath()+"/Invoices/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File myExternalFile = new File(dir.getPath(), "invoice-"+new Date().toString()+".pdf");
            System.out.println("PAAAAAAAAAATH" + myExternalFile.getAbsolutePath());
            try {
                document.writeTo(new FileOutputStream(myExternalFile));

//                fos.write(m.getText().toString().getBytes())
            } catch (IOException e) {
                e.printStackTrace();
            }

            document.close();

        }


    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
