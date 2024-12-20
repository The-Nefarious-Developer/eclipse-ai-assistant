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
 * The activator class controls the plug-in lifecycle for the zJoule plugin.
 * <p>
 * This class extends {@link AbstractUIPlugin} and serves as the entry point
 * for the plug-in. It manages the initialization of various memory components
 * and provides a shared instance for use across the plugin.
 */
public class Activator extends AbstractUIPlugin {

    /** The plug-in ID for the zJoule plugin. */
    public static final String PLUGIN_ID = "com.developer.nefarious.zjoule"; //$NON-NLS-1$

    /** The shared instance of the {@code Activator}. */
    private static Activator plugin;

    /**
     * Returns the shared instance of the {@code Activator}.
     *
     * @return the shared {@code Activator} instance.
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * The default constructor for the {@code Activator}.
     */
    public Activator() {
    }

    /**
     * Initializes memory resources used throughout the plugin.
     * <p>
     * This method sets up resources for chat operations, login management,
     * and temporary memory storage by leveraging {@link ObjectSerializer} and
     * {@link EclipseMemory}.
     */
    private void initialize() {
        ObjectSerializer objectSerializer = new ObjectSerializer();
        EclipseMemory eclipseMemory = new EclipseMemory();

        // Initialize memory resources for chat consumption
        MemoryAccessToken.initialize(objectSerializer, eclipseMemory);
        MemoryServiceKey.initialize(objectSerializer, eclipseMemory);
        MemoryResourceGroup.initialize(eclipseMemory);
        MemoryDeployment.initialize(objectSerializer, eclipseMemory);
        MemoryMessageHistory.initialize(objectSerializer, eclipseMemory);

        // Initialize memory resources for login operation
        TemporaryMemoryAccessToken.initialize(objectSerializer, eclipseMemory);
        TemporaryMemoryServiceKey.initialize(objectSerializer, eclipseMemory);
        TemporaryMemoryResourceGroup.initialize(eclipseMemory);
        TemporaryMemoryDeployment.initialize(objectSerializer, eclipseMemory);
    }

    /**
     * Starts the plugin by initializing the shared instance and memory resources.
     *
     * @param context the OSGi bundle context.
     * @throws Exception if the plugin fails to start.
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        initialize();
    }

    /**
     * Stops the plugin by clearing the shared instance.
     *
     * @param context the OSGi bundle context.
     * @throws Exception if the plugin fails to stop.
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }
}
