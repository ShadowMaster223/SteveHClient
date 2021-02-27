/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.world;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.client.sound.SoundInstance;

public class PlaySoundEvent extends Cancellable {
    private static final PlaySoundEvent INSTANCE = new PlaySoundEvent();

    public SoundInstance sound;

    public static PlaySoundEvent get(SoundInstance sound) {
        INSTANCE.setCancelled(false);
        INSTANCE.sound = sound;
        return INSTANCE;
    }
}
