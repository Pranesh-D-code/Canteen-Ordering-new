package com.canteen.util;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;

public class SpeechUtil {

    public static void speak(String text) {
        try {
            // ✅ Encode text for URL
            String encoded = java.net.URLEncoder.encode(text, "UTF-8");

            // ✅ Use Google Translate TTS (free public endpoint)
            String urlStr = "https://translate.google.com/translate_tts?ie=UTF-8&q="
                    + encoded + "&tl=en&client=tw-ob";

            // ✅ Fetch the audio
            URL url = new URL(urlStr);
            try (InputStream audioSrc = url.openStream();
                 InputStream bufferedIn = new BufferedInputStream(audioSrc);
                 AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn)) {

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                // wait till playback ends
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            }

        } catch (Exception e) {
            System.out.println("Speech error: " + e.getMessage());
        }
    }
}
