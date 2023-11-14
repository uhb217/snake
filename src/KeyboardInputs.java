import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyboardInputs implements KeyListener{
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == VK_W){
            gamePanel.setDirection('U');
        }else if (e.getKeyCode() == VK_S){
            gamePanel.setDirection('D');
        }else if (e.getKeyCode() == VK_A){
            gamePanel.setDirection('L');
        }else if (e.getKeyCode() == VK_D){
            gamePanel.setDirection('R');
        }else if (e.getKeyCode() == VK_SPACE){
            if (!gamePanel.play) gamePanel.play = true;
            else gamePanel.play = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
