package ui.service;


import ui.presenter.Presenter;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private static Dispatcher instance;
    private Map<String, Presenter> presenterMap;
    private JFrame mainFrame;
    private Presenter presenter;

    public Dispatcher(JFrame mainFrame) {
        instance = this;
        this.mainFrame = mainFrame;
        presenterMap = new HashMap<>();
        mainFrame.addPropertyChangeListener("name", e -> present((String) e.getNewValue()));
    }

    public void present(String name) {
        presenter = presenterMap.get(name);
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(presenter.presentView());
        presenter.execute();
        mainFrame.revalidate();
    }

    public void registerPresenter(Presenter presenter) {
        presenterMap.put(presenter.getPath(), presenter);
    }

    public static Dispatcher getInstance() {
        return instance;
    }
}
