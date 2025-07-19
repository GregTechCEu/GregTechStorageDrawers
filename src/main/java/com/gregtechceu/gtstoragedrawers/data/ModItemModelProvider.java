package com.gregtechceu.gtstoragedrawers.data;

import com.gregtechceu.gtstoragedrawers.DrawerTypes;
import com.gregtechceu.gtstoragedrawers.GregTechStorageDrawers;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, GregTechStorageDrawers.MODID, helper);
    }

    @Override
    protected void registerModels() {
        for (DrawerTypes type : DrawerTypes.values()) {
            withExistingParent(type.getTrimItemName(), modLoc(type.getTrimModelName()));
            withExistingParent(type.getItemModelName(1, false), modLoc(type.getDrawerModelName(1, false)));
            withExistingParent(type.getItemModelName(2, false), modLoc(type.getDrawerModelName(2, false)));
            withExistingParent(type.getItemModelName(4, false), modLoc(type.getDrawerModelName(4, false)));
            withExistingParent(type.getItemModelName(1, true), modLoc(type.getDrawerModelName(1, true)));
            withExistingParent(type.getItemModelName(2, true), modLoc(type.getDrawerModelName(2, true)));
            withExistingParent(type.getItemModelName(4, true), modLoc(type.getDrawerModelName(4, true)));
        }
    }
}
