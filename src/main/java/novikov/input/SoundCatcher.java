package novikov.input;

import novikov.entity.SoundCatcherResult;

public interface SoundCatcher {

    /**
     * Method is catching any loud sound from all computer.
     *
     * @return result likes 'I catch something' or 'Time is over'
     */
    SoundCatcherResult catchLoudSound();

}
