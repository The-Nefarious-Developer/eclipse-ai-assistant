package com.developer.nefarious.zjoule.memory;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class EclipseMemoryStorage implements IEclipseMemoryStorage {
	
	private static EclipseMemoryStorage INSTANCE = new EclipseMemoryStorage();
	
	private EclipseMemoryStorage() {}
	
	public static EclipseMemoryStorage getInstance() {
		return INSTANCE;
	}

	@Override
	public IEclipsePreferences getEclipsePreferences() {
		return InstanceScope.INSTANCE.getNode(PLUGIN_ID);
	}

}
