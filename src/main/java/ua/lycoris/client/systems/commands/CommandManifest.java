package ua.lycoris.client.systems.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandManifest {
    String name();
    String description() default "";
    int max() default 1; int min() default 0;
    String leghtExeption() default "Invalid arguments length";
}
