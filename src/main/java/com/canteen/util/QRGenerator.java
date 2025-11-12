package com.canteen.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRGenerator {
    public static String generate(String text, String fileName) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 350, 350);
        Path dir = Paths.get("qrcodes");
        if (!Files.exists(dir)) Files.createDirectories(dir);
        Path out = dir.resolve(fileName + ".png");
        MatrixToImageWriter.writeToPath(matrix, "PNG", out);
        return out.toAbsolutePath().toUri().toString(); // file:/... used by JavaFX Image
    }
}
