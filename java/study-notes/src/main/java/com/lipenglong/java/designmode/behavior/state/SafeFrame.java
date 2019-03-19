package com.lipenglong.java.designmode.behavior.state;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class SafeFrame extends Frame implements ActionListener, Context {
    private TextField textClock = new TextField(60);
    private TextArea textArea = new TextArea(10, 60);
    private Button btnUse = new Button("使用金库");
    private Button btnAlarm = new Button("按下警铃");
    private Button btnPhone = new Button("正常通话");
    private Button btnExit = new Button("结束");

    private State state = DayState.getInstance();

    public SafeFrame(String title) throws HeadlessException {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        add(textClock, BorderLayout.NORTH);
        textClock.setEditable(false);
        add(textArea, BorderLayout.CENTER);
        textArea.setEditable(false);

        Panel panel = new Panel();
        panel.add(btnUse);
        panel.add(btnAlarm);
        panel.add(btnPhone);
        panel.add(btnExit);
        add(panel, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        btnUse.addActionListener(this);
        btnAlarm.addActionListener(this);
        btnPhone.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void setClock(int hour) {
        String clockStr = "现在时间是：";
        if (hour < 10) {
            clockStr += "0" + hour + ":00";
        } else {
            clockStr += hour + ":00";
        }
        System.out.println(clockStr);
        textClock.setText(clockStr);
        state.doClock(this, hour);
    }

    @Override
    public void changeState(State state) {
        System.out.println("从 " + this.state + " 状态变为了 " + state + " 状态。");
        this.state = state;
    }

    @Override
    public void recordLog(String msg) {
        textArea.append("record... " + msg + "\n");
    }

    @Override
    public void callSecurityCenter(String msg) {
        textArea.append("call! " + msg + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        if (e.getSource() == btnUse) {
            state.doUse(this);
        } else if (e.getSource() == btnAlarm) {
            state.doAlarm(this);
        } else if (e.getSource() == btnPhone) {
            state.doPhone(this);
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        } else {
            System.out.println("?");
        }
    }
}
