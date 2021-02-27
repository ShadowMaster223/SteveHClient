/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.misc;


import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.settings.*;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.client.network.ClientPlayerEntity;

public class AnnoyHack extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> name = sgGeneral.add(new StringSetting.Builder()
            .name("name")
            .description("Toggle Activate to update.")
            .defaultValue("stevehamish")
            .build()
    );

    
    public AnnoyHack() {
        super(Category.Misc, "annoy-hack", "Repeats a certain players messages");
    }

    
    String target =  (mc.getSession().getUsername());
    @Override
    public void onActivate() {
    	ClientPlayerEntity player = mc.player;
        if(player != null && target.equals(name.get().toString())) 
    	    ChatUtils.warning("Annoying yourself is a bad idea!");
        else ChatUtils.info("Now annoying " + name.get() + ".");
    }

/*		 message1 = String.valueOf(message1);
		int beginIndex = message1.indexOf(prefix) + prefix.length();
		String repeated = message1.substring(beginIndex);
		
		if(message1.startsWith(name.get()))
			mc.player.sendChatMessage(repeated);
			return;
		
		String prefix1 = name.get() + ">";
*		if(message.contains("<" + prefix1) || message.contains(prefix1))
*		{
*			repeat(message, prefix1);
			return;
		}
		
		String prefix2 = name.get() + ":";
		if(message.contains("] " + prefix2) || message.contains("]" + prefix2))
			repeat(message, prefix2);
	}
	
	private void repeat(String message1, String prefix)
	{
		int beginIndex = message1.indexOf(prefix) + prefix.length();
		String repeated = message1.substring(beginIndex);
		mc.player.sendChatMessage(repeated);
*/	
}
