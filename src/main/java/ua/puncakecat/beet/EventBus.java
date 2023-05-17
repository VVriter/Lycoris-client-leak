package ua.puncakecat.beet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;

/**
    @author PunCakeCat
*/
public class EventBus {
    private ArrayList<Listener> listeners;
    private int size = 0;
    public EventBus(){
        this.listeners = new ArrayList<>();
    }

    public void register(Object target){
        Field[] fields = target.getClass().getDeclaredFields();
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            Field field = fields[i];
            if(field.isAnnotationPresent(Subscribe.class)) {
                if (Listener.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    Listener listener = null;
                    try {
                        listener = (Listener) field.get(target);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    listener.setPriority(field.getAnnotation(Subscribe.class).priority());
                    subscribe(listener);
                }else
                    throw new EventBusException("Attempt to subscribe no subscribe able object");
            }
        }
    }

    public void unregister(Object target){
        Field[] fields = target.getClass().getDeclaredFields();
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            Field field = fields[i];
            if(field.isAnnotationPresent(Subscribe.class)) {
                if (Listener.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    Listener listener = null;
                    try {
                        listener = (Listener) field.get(target);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    unsubscribe(listener);
                }else
                    throw new EventBusException("Attempt to subscribe no subscribe able field");
            }
        }
    }

    public void subscribe(Listener<?> listener){
        listeners.add(listener);
        listeners.sort(Comparator.comparing(listener1 -> -listener1.getPriority().ordinal()));
    }

    public void unsubscribe(Listener<?> listener){
        listeners.remove(listener);
        listeners.sort(Comparator.comparing(listener1 -> -listener1.getPriority().ordinal()));
        size -= 1;
    }

    public void post(Event event){
        size = listeners.size();
        for(int i = 0; i < size; i++){
            Listener<Event> listener = listeners.get(i);
            if(event.getClass().isAssignableFrom(listener.getTarget()))
                listener.getCallback().accept(event);
        }
    }
}
