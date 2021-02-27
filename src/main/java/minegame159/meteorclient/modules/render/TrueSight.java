/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.render;

//Updated by squidoodly 03/07/2020
//Updated by squidoodly 30/07/2020
//Rewritten (kinda (:troll:)) by snale 07/02/2021

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;

public class TrueSight extends Module {
    public enum Position {
        Above,
        OnTop
    }
    public TrueSight() {
        super(Category.Render, "TrueSight", "Allows you to see invisible entities and barriers");
    }
}
  