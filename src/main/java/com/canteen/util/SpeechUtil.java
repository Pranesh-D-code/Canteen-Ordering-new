package com.canteen.util;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechUtil {

    private static final String VOICE_NAME = "kevin16";

    public static void speak(String text) {
        try {
            Voice voice = VoiceManager.getInstance().getVoice(VOICE_NAME);
            if (voice == null) {
                System.err.println("Voice not found: " + VOICE_NAME);
                return;
            }
            voice.allocate();
            voice.speak(text);
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
