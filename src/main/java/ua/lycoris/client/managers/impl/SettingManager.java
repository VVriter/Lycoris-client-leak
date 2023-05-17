package ua.lycoris.client.managers.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ua.lycoris.client.managers.Manager;
import ua.lycoris.client.systems.modules.Module;
import ua.lycoris.client.settings.Setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SettingManager extends Manager {

    public Multimap<Module, Setting> settings;

    public SettingManager(){
        settings = ArrayListMultimap.create();
    }

    public List<Setting> getSettings(Module module){
        return settings.entries().stream().filter(entry -> entry.getKey() == module)
                .map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Module getModule(Setting setting){
        return settings.entries().stream().filter(entry -> entry.getValue() == setting)
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }
}
