/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.utils.misc;


import net.minecraft.nbt.CompoundTag;

public interface ISerializable<T> {
    CompoundTag toTag();

    T fromTag(CompoundTag tag);


}
