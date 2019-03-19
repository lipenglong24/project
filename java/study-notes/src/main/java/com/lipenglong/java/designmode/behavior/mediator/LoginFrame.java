package com.lipenglong.java.designmode.behavior.mediator;

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
public class LoginFrame extends Frame implements ActionListener, Mediator {
    private ColleagueCheckbox checkGuest;
    private ColleagueCheckbox checkLogin;
    private ColleagueTextField textUser;
    private ColleagueTextField textPwd;
    private ColleagueButton btnOk;
    private ColleagueButton btnCancel;

    public LoginFrame(String title) throws HeadlessException {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(4, 2));
        createColleagues();
        add(checkGuest);
        add(checkLogin);
        add(new Label("Username:"));
        add(textUser);
        add(new Label("Password:"));
        add(textPwd);
        add(btnOk);
        add(btnCancel);
        colleaguesChange();
        pack();
        show();
    }

    @Override
    public void createColleagues() {
        CheckboxGroup g = new CheckboxGroup();
        checkGuest = new ColleagueCheckbox("Guest", g, true);
        checkLogin = new ColleagueCheckbox("Login", g, false);
        textUser = new ColleagueTextField("", 10);
        textPwd = new ColleagueTextField("", 10);
        textPwd.setEchoChar('*');
        btnOk = new ColleagueButton("OK");
        btnCancel = new ColleagueButton("Cancel");

        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPwd.setMediator(this);
        btnOk.setMediator(this);
        btnCancel.setMediator(this);

        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);
        textUser.addTextListener(textUser);
        textPwd.addTextListener(textPwd);
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    @Override
    public void colleaguesChange() {
        if (checkGuest.getState()) {
            textUser.setColleagueEnabled(false);
            textPwd.setColleagueEnabled(false);
            btnOk.setColleagueEnabled(true);
        } else {
            textUser.setColleagueEnabled(true);
            userPwdChanged();
        }
    }

    private void userPwdChanged() {
        if (textUser.getText().length() > 0) {
            textPwd.setColleagueEnabled(true);
            if (textPwd.getText().length() > 0) {
                btnOk.setColleagueEnabled(true);
            } else {
                btnOk.setColleagueEnabled(false);
            }
        } else {
            textPwd.setColleagueEnabled(false);
            btnOk.setColleagueEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        System.exit(0);
    }
}
