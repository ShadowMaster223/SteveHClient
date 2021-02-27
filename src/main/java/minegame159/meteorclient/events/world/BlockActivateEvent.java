/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.world;

import net.minecraft.block.BlockState;

public class BlockActivateEvent {
    private static final BlockActivateEvent INSTANCE = new BlockActivateEvent();

    public BlockState blockState;

    public static BlockActivateEvent get(BlockState blockState) {
        INSTANCE.blockState = blockState;
        return INSTANCE;
    }
}
