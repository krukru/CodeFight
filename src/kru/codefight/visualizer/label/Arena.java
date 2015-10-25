package kru.codefight.visualizer.label;

import javax.management.timer.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Arena extends JDialog {
  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;

  @Override
  public JPanel getContentPane() {
    return contentPane;
  }

  public JLabel getRedHp() {
    return redHp;
  }

  public JLabel getRedStamina() {
    return redStamina;
  }

  public JLabel getRedStance() {
    return redStance;
  }

  public JLabel getBlueHp() {
    return blueHp;
  }

  public JLabel getBlueStamina() {
    return blueStamina;
  }

  public JLabel getBlueStance() {
    return blueStance;
  }

  public JLabel getRedCasting() {
    return redCasting;
  }

  public JLabel getBlueCasting() {
    return blueCasting;
  }

  private JLabel redHp;
  private JLabel redStamina;
  private JLabel redStance;
  private JLabel blueHp;
  private JLabel blueStamina;
  private JLabel blueStance;
  private JLabel redCasting;
  private JLabel blueCasting;

  private LinkedList<Consumer<Arena>> pendingMoves = new LinkedList<>();

  public Arena() {
    setContentPane(contentPane);
    setModal(false);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {onOK();}
    });

    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {onCancel();}
    });

// call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

// call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

  }

  public void enqueueChange(Consumer<Arena> change) {
    pendingMoves.addLast(change);

  }

  private void nextAction() {
    pendingMoves.getFirst().accept(this);
    pendingMoves.removeFirst();
  }

  private void onOK() {
    buttonOK.setEnabled(false);
    Timer t = new Timer(1000, new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nextAction();
      }
    });
    t.setDelay(1000);
    t.setRepeats(true);
    t.start();
  }

  private void onCancel() {
// add your code here if necessary
    dispose();
    System.exit(0);
  }

}
