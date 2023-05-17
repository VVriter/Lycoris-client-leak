package ua.lycoris.client.managers.impl;

import ua.lycoris.client.Lycoris;
import ua.lycoris.client.managers.Manager;
import ua.lycoris.client.systems.modules.Module;
import ua.lycoris.client.settings.Setting;
import ua.lycoris.client.utils.ClassFinder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleManager extends Manager {

    public List<Module> modules;

    public ModuleManager(){
        modules = new ArrayList<>();
        initializeModules();
        loadModulesSettings();
    }

    private void initializeModules(){
        Set<Class> classList = ClassFinder.findClasses(Module.class.getPackage().getName(), Module.class);
        classList.forEach(aClass -> {
            try {
                Module module = (Module) aClass.getConstructor().newInstance();
                modules.add(module);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Couldn't initiate module " + aClass.getSimpleName() + "! Err: " + e.getClass().getSimpleName() + ", message: " + e.getMessage());
            }
        });
        System.out.println("Modules initialised");
        modules.sort(Comparator.comparing(Module::getName));
    }

    private void loadModulesSettings(){
        modules.forEach(m -> {
            loadFields(m.getClass().getSuperclass().getDeclaredFields(), m);
            loadFields(m.getClass().getDeclaredFields(), m);
        });
    }

    private void loadFields(Field[] fields, Module m){
        for(Field field : fields) {
            if (Setting.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    Setting setting = (Setting) field.get(m);
                    Lycoris.settingManager.settings.put(m, setting);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Module> getModules(Module.Category category){
        return modules.stream().filter(module -> module.getCategory() == category).collect(Collectors.toList());
    }
}
