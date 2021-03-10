/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.settings.*;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.Text;
import java.util.Objects;

public class PopBob extends Module {
    public enum Mode{ 
        PopBob,
        Eleven
    }

    public PopBob(){
        super(Category.Misc, "popbob", "Some dupes I guess"); 
    }

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Mode> mode = sgGeneral.add(new EnumSetting.Builder<Mode>() 
            .name("mode")
            .description("The mode of the book bot.")
            .defaultValue(Mode.PopBob)
            .build()
    );
    private int booksLeft = 1;
    @Override
    public void onDeactivate() {
        booksLeft = 1;
        new ListTag();
    }

    
    @EventHandler
    private void onTick(TickEvent.Post event) {
        if(mc.currentScreen instanceof HandledScreen<?>) return;
        if(booksLeft <= 0){
            toggle();
            return;
        }

        if(mode.get() == Mode.PopBob){
                
        	 new Thread(() -> {
                 for (int i = 0; i < ((9 * 4) - 1); i++) {

                     mc.player.inventory.setStack(i, mc.player.inventory.getMainHandStack());
                     for (int ii = 0; ii < 64; ii++) {
                         mc.player.inventory.getStack(i).setCount(ii);
                         try {
                             Thread.sleep(10);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }
                
        	 }).start();
        }else if(mode.get() == Mode.Eleven){
                
        	 assert mc.player != null;
             mc.player.dropSelectedItem(true);
             Objects.requireNonNull(mc.getNetworkHandler()).getConnection().disconnect(Text.of("Steve Hamish"));
                 
        
        	 }
    }
}