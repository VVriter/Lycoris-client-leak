package ua.lycoris.client.systems.modules;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleManifest{
    String name();
    Module.Category category();
    String description() default "";
}
