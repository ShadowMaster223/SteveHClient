/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.render.hud.modules;

import minegame159.meteorclient.Config;
import minegame159.meteorclient.modules.render.hud.HUD;

public class WatermarkHud extends DoubleTextHudModule {
    public WatermarkHud(HUD hud) {
        super(hud, "watermark", "Displays a watermark.", "SteveH Client ");
    }

    @Override
    protected String getRight() {
        return Config.get().version.getOriginalString();
    }
}
