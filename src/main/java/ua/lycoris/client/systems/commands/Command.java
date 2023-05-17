package ua.lycoris.client.systems.commands;

import jdk.nashorn.internal.objects.annotations.Getter;
import ua.lycoris.client.utils.interfaces.Globals;

public class Command implements Globals {

    private String name;
    private String description;

    private int min;
    private int max;
    private String leghtError;

    public void execute(String[] args){}

    public Command(){
        if(!getClass().isAnnotationPresent(CommandManifest.class))
            throw new CommandException("Didn't find any command manifest annotation");
        else{
            CommandManifest manifest = getClass().getAnnotation(CommandManifest.class);
            this.name = manifest.name();
            this.description = manifest.description();
            this.min = manifest.min();
            this.max = manifest.max();
            this.leghtError = manifest.leghtExeption();
        }
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

    @Getter
    public String getLeghtError() {
        return leghtError;
    }
}
