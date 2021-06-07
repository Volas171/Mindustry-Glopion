/*******************************************************************************
 * Copyright 2021 Itzbenz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.o7.Fire.Glopion.Module.Patch;

import Atom.Reflect.Reflect;
import Atom.Utility.Digest;
import arc.Core;
import arc.Events;
import arc.scene.ui.Dialog;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Fonts;
import mindustry.ui.MobileButton;
import mindustry.ui.Styles;
import mindustry.ui.fragments.MenuFragment;
import org.o7.Fire.Glopion.GlopionCore;
import org.o7.Fire.Glopion.Internal.Interface;
import org.o7.Fire.Glopion.Module.ModsModule;
import org.o7.Fire.Glopion.Patch.Translation;
import org.o7.Fire.Glopion.UI.*;

import static mindustry.Vars.ui;

public class UIPatch extends ModsModule {
    public static Dialog.DialogStyle ozoneStyle;
    
    {
        dependency.add(VarsPatch.class);
        dependency.add(Translation.class);
    }
    
    private void onResize() {
        if (VarsPatch.menu != null){
            if (Vars.testMobile) try {
                Reflect.getMethod(MenuFragment.class, "buildMobile", ui.menufrag).invoke(ui.menufrag);
            }catch(Throwable ignored){}
            if (Vars.mobile || Vars.testMobile){
                if (Core.graphics.isPortrait()) VarsPatch.menu.row();
                VarsPatch.menu.add(new MobileButton(Icon.info, Translation.get("Ozone"), () -> GlopionCore.modsMenu.show()));
            }else{
                
                VarsPatch.menu.button(Translation.get("Ozone"), Icon.file, GlopionCore.modsMenu::show).growX().bottom();
            }
        }
    }
    
    
    @Override
    public void start() {
        ozoneStyle = new Dialog.DialogStyle() {
            {
                stageBackground = Styles.none;
                titleFont = Fonts.def;
                background = Tex.windowEmpty;
                titleFontColor = Pal.accent;
            }
        };
        if (GlopionCore.test) return;
        //GlopionCore.commFrag = new CommandsListFrag();
        ModsMenu.add(new BundleViewer());
        ModsMenu.add(new WorldInformation());
        ModsMenu.add(new OzonePlaySettings());
        ModsMenu.add(new OzoneMenu(Translation.get("ozone.hud"), ozoneStyle));
        ModsMenu.add(GlopionCore.worldInformation = new EnvironmentInformation());//mmm
        ModsMenu.add(new LogView());
        ModsMenu.add(new ExperimentDialog());
        
        GlopionCore.modsMenu = new ModsMenu();
        //GlopionCore.commFrag.build(Vars.ui.hudGroup);
        ui.logic.buttons.button("Show Hash", Icon.list, () -> {
            new ScrollableDialog("Hash Code") {
                @Override
                protected void setup() {
                    String src = ui.logic.canvas.save();
                    int hash = src.hashCode();
                    long lhash = Digest.longHash(src);
                    table.button(hash + "", () -> {
                        Interface.copy(hash + "");
                    }).tooltip("Copy").growY();
                    table.button(lhash + "", () -> {
                        Interface.copy(lhash + "");
                    }).tooltip("Copy").growY();
                }
            }.show();
        }).size(210f, 64f);
        
        Events.on(EventType.ResizeEvent.class, c -> {
            onResize();
        });
        onResize();
    }
    
}