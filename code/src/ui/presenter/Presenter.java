package ui.presenter;

import ui.service.Dispatcher;
import ui.view.View;

import java.awt.*;

public abstract class Presenter<V extends View> {

    private V view;
    private static String port;

    public Presenter(V view) {
        this.view = view;
    }

    public Component presentView() {
        return view.getComponent();
    }

    public V getView() {
        return view;
    }

    public abstract String getPath();

    public void execute() {}

    protected String getPort(){
        if (port == null) return null;
        else return port;
    }

    protected void setPort(String port){
        this.port = port;
    }

    protected void goTo(String name) {
        Dispatcher.getInstance().present(name);
    }
}
