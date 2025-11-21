package com.canteen.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class QRGenerator {

    public static Image generateQR(String text) {

        int size = 300;

        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size);

            WritableImage image = new WritableImage(size, size);
            PixelWriter writerFX = image.getPixelWriter();

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    boolean black = matrix.get(x, y);
                    writerFX.setArgb(x, y, black ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            return image;

        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
