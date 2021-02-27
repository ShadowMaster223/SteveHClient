/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.modules.Module;

public class ModuleVisibilityChangedEvent {
    private static final ModuleVisibilityChangedEvent INSTANCE = new ModuleVisibilityChangedEvent();

    public Module module;

    public static ModuleVisibilityChangedEvent get(Module module) {
        INSTANCE.module = module;
        return INSTANCE;
    }
}
