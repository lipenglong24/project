package com.lipenglong.java.designmode.behavior.command;

import javax.swing.*;
import java.awt.event.*;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main extends JFrame implements ActionListener, MouseMotionListener, WindowListener {
    private MacroCommand history = new MacroCommand();
    private DrawCanvas drawCanvas = new DrawCanvas(400, 400, history);
    private JButton clearBtn = new JButton("clear");

    public Main(String title) {
        super(title);

        this.addWindowListener(this);
        drawCanvas.addMouseMotionListener(this);
        clearBtn.addActionListener(this);

        Box btnBox = new Box(BoxLayout.X_AXIS);
        btnBox.add(clearBtn);
        Box mainBox = new Box(BoxLayout.Y_AXIS);
        mainBox.add(btnBox);
        mainBox.add(drawCanvas);
        getContentPane().add(mainBox);

        pack();
        show();
    }

    public static void main(String[] args) {
        new Main("命令模式示例");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            history.clear();
            drawCanvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Command cmd = new DrawCommand(drawCanvas, e.getPoint());
        history.append(cmd);
        cmd.execute();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
