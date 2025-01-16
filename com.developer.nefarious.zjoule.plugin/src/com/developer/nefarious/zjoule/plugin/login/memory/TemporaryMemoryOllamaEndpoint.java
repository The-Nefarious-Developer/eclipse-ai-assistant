package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaEndpoint;

public class TemporaryMemoryOllamaEndpoint implements IMemoryObject<String>, ITemporaryMemoryObject {

    private static TemporaryMemoryOllamaEndpoint instance;

    public static final String KEY = "tmp-" + MemoryOllamaEndpoint.KEY;

    private IEclipseMemory eclipseMemory;

    public static TemporaryMemoryOllamaEndpoint getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryOllamaEndpoint not initialized. Call initialize() first.");
        }
        return instance;
    }

    public static void initialize(final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryOllamaEndpoint(eclipseMemory);
        }
    }

    public static void resetInstance() {
        instance = null;
    }

    private TemporaryMemoryOllamaEndpoint(final IEclipseMemory eclipseMemory) {
        this.eclipseMemory = eclipseMemory;
    }

    @Override
    public Boolean isEmpty() {
        String ollamaEndpoint = load();
        if ((ollamaEndpoint == null) || ollamaEndpoint.isEmpty() || ollamaEndpoint.isBlank()) {
            return true;
        }
        return false;
    }

    @Override
    public String load() {
        return eclipseMemory.loadFromEclipsePreferences(KEY);
    }

    @Override
    public void persist() {
        String ollamaEndpoint = eclipseMemory.loadFromEclipsePreferences(KEY);
        eclipseMemory.saveOnEclipsePreferences(MemoryOllamaEndpoint.KEY, ollamaEndpoint);
    }

    @Override
    public void save(final String ollamaEndpoint) {
        eclipseMemory.saveOnEclipsePreferences(KEY, ollamaEndpoint);
    }
    
}
