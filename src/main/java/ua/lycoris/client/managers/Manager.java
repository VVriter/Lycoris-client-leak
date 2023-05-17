package ua.lycoris.client.managers;

import ua.lycoris.client.Lycoris;
import ua.lycoris.client.utils.interfaces.Globals;

public class Manager implements Globals {
    public void load(){
        Lycoris.EVENT_BUS.register(this);
    }

    public void destroy(){
        Lycoris.EVENT_BUS.unregister(this);
    }
}
