package com.developer.nefarious.zjoule.plugin.memory;

public class MemoryOllamaEndpoint implements IMemoryObject<String> {
	
    public static final String KEY = "ollama-endpoint";

    private static MemoryOllamaEndpoint instance;

    private IEclipseMemory eclipseMemory;

    public static MemoryOllamaEndpoint getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryOllamaEndpoint not initialized. Call initialize() first.");
        }
        return instance;
    }

    public static void initialize(final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryOllamaEndpoint(eclipseMemory);
        }
    }

    public static void resetInstance() {
        instance = null;
    }

    private MemoryOllamaEndpoint(final IEclipseMemory eclipseMemory) {
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
    public void save(final String ollamaEndpoint) {
        eclipseMemory.saveOnEclipsePreferences(KEY, ollamaEndpoint);
    }
    
    @Override
    public void clear() {
    	eclipseMemory.deleteFromEclipsePreferences(KEY);
    }

}
