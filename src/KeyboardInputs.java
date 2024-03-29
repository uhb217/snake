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
        if (e.getKeyCode() == VK_W || e.getKeyCode() == VK_UP){
            gamePanel.setDirection('U');
        }else if (e.getKeyCode() == VK_S || e.getKeyCode() == VK_DOWN){
            gamePanel.setDirection('D');
        }else if (e.getKeyCode() == VK_A || e.getKeyCode() == VK_LEFT){
            gamePanel.setDirection('L');
        }else if (e.getKeyCode() == VK_D || e.getKeyCode() == VK_RIGHT){
            gamePanel.setDirection('R');
        }else if (e.getKeyCode() == VK_SPACE){
            if (!gamePanel.play) gamePanel.play = true;
            else gamePanel.play = false;
        }
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
