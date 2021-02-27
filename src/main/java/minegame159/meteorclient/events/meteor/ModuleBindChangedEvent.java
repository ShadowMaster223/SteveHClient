/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.modules.Module;

public class ModuleBindChangedEvent {
    private static final ModuleBindChangedEvent INSTANCE = new ModuleBindChangedEvent();

    public Module module;

    public static ModuleBindChangedEvent get(Module module) {
        INSTANCE.module = module;
        return INSTANCE;
    }
}
