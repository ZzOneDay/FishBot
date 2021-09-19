package novikov.actions;

import novikov.entity.results.ClickResult;

public interface ClickButton {

    /***
     * Use this method for click on hook.
     * example: rightClick is InputEvent.BUTTON3_DOWN_MASK = 4096
     *
     * @return result of click.
     */
    ClickResult click(int buttonId);
}
