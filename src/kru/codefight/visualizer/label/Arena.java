package kru.codefight.visualizer.label;

import javax.swing.*;
import java.awt.event.*;

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

  public Arena() {
    setContentPane(contentPane);
    setModal(true);
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

  private void onOK() {
// add your code here
    dispose();
  }

  private void onCancel() {
// add your code here if necessary
    dispose();
  }

  public static void main(String[] args) {
    Arena arena = new Arena();
    arena.pack();
    arena.setLocationRelativeTo(null);
    arena.setVisible(true);
    System.exit(0);
  }
}
