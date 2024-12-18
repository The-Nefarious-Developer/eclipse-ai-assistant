package com.developer.nefarious.zjoule.plugin.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.developer.nefarious.zjoule.plugin.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.memory.EclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.memory.utils.ObjectSerializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.developer.nefarious.zjoule"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * The constructor
	 */
	public Activator() {
	}

	private void initialize() {
		ObjectSerializer objectSerializer = new ObjectSerializer();
		EclipseMemory eclipseMemory = new EclipseMemory();

		// Memory resources for chat consumption
		MemoryAccessToken.initialize(objectSerializer, eclipseMemory);
		MemoryServiceKey.initialize(objectSerializer, eclipseMemory);
		MemoryResourceGroup.initialize(eclipseMemory);
		MemoryDeployment.initialize(objectSerializer, eclipseMemory);
		MemoryMessageHistory.initialize(objectSerializer, eclipseMemory);

		// Memory resources for login operation
		TemporaryMemoryAccessToken.initialize(objectSerializer, eclipseMemory);
		TemporaryMemoryServiceKey.initialize(objectSerializer, eclipseMemory);
		TemporaryMemoryResourceGroup.initialize(eclipseMemory);
		TemporaryMemoryDeployment.initialize(objectSerializer, eclipseMemory);
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		initialize();
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

}
