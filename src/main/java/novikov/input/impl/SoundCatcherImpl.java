package novikov.input.impl;

import novikov.entity.SoundCatcherResult;
import novikov.input.SoundCatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;

@Service
public class SoundCatcherImpl implements SoundCatcher {
    private static final int LIMIT_WAITING_TIMES = 6000;

    @Autowired
    private AudioFormat audioFormat;

    @Override
    public SoundCatcherResult catchLoudSound() {
        return captureAudio();
    }

    /**
     * Idea of this method is listening line by mixer.
     * System use itself for recording and making sounds.
     * If application catch any loud sound, it will return result.
     * We have 2 variants of result,
     * fish on the hook and the time limit is over.
     *
     * @return result of process
     */
    private SoundCatcherResult captureAudio() {
        boolean fishOnHook = false;
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            byte[] data = new byte[targetDataLine.getBufferSize()];
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            int times = 0;
            while (times < LIMIT_WAITING_TIMES) {
                Thread.sleep(10);
                times++;
                int numBytesRead = targetDataLine.read(data, 0, data.length);
                outputStream.write(data, 0, numBytesRead);
                int volume = getVolume(data);
                if (volume > 200) {
                    fishOnHook = true;
                    break;
                }
            }

            outputStream.close();
            targetDataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fishOnHook ? SoundCatcherResult.FISH_ON_HOOK : SoundCatcherResult.TIME_IS_LEFT;
    }

    /**
     * Get volume of line is hard work,
     * because not any mixer is able to give volume.
     * My idea is counting bites in buffer.
     *
     * @param data arrays of bite with sound
     * @return sum of bite in arrays, likes as volume
     */
    private Integer getVolume(byte[] data) {
        int size = 0;
        for (byte oneByte : data) {
            size += Math.abs(oneByte);
        }
        return size / 1000;
    }
}
