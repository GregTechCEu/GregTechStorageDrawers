package com.gregtechceu.gtstoragedrawers;

import com.gregtechceu.gtceu.GTCEu;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import com.jaquadro.minecraft.storagedrawers.core.ModBlockVariants;
import com.jaquadro.minecraft.storagedrawers.core.ModBlockVariants.VariantData;
import com.texelsaurus.minecraft.chameleon.registry.ChameleonRegistry;
import lombok.Getter;

public enum DrawerTypes {

    RUBBER("rubber", "rubber_planks", "rubber_slab"),
    TREATED("treated", "treated_wood_planks", "treated_wood_slab"),

    ;

    @Getter
    private final String name;
    @Getter
    private final ResourceLocation plankResource;
    @Getter
    private final ResourceLocation slabResource;
    @Getter
    private final VariantData data;

    DrawerTypes(String name, String plankName, String slabName) {
        this.name = name;
        this.plankResource = new ResourceLocation(GTCEu.MOD_ID, plankName);
        this.slabResource = new ResourceLocation(GTCEu.MOD_ID, slabName);
        this.data = new VariantData(new ResourceLocation(GregTechStorageDrawers.MODID, GTCEu.MOD_ID + "_" + name));
    }

    public String getTrimModelName() {
        return "block/" + GTCEu.MOD_ID + "_" + name + "_trim";
    }

    public String getTrimItemName() {
        return "item/" + GTCEu.MOD_ID + "_" + name + "_trim";
    }

    public String getDrawerModelName(int size, boolean half) {
        String type = half ? "half" : "full";
        return "block/" + GTCEu.MOD_ID + "_" + name + "_" + type + "_drawers_" + size;
    }

    public String getItemModelName(int size, boolean half) {
        String type = half ? "half" : "full";
        return "item/" + GTCEu.MOD_ID + "_" + name + "_" + type + "_drawers_" + size;
    }

    public String getTextureName(String textureVariant) {
        return "block/" + GTCEu.MOD_ID + "/drawers_" + name + "_" + textureVariant;
    }

    public void registerBlocks(ChameleonRegistry<Block> register) {
        ModBlockVariants.registerVariant(register, data);
    }

    public void registerItems(ChameleonRegistry<Item> register) {
        ModBlockVariants.registerVariantItem(register, data);
    }
}
