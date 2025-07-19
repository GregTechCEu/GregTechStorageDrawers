package com.gregtechceu.gtstoragedrawers.data;

import com.gregtechceu.gtstoragedrawers.DrawerTypes;
import com.gregtechceu.gtstoragedrawers.GregTechStorageDrawers;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, GregTechStorageDrawers.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DrawerTypes type : DrawerTypes.values()) {
            registerType(type);
        }
    }

    void registerType(DrawerTypes type) {
        BlockModelBuilder blockTrim = models().cubeAll(type.getTrimModelName(), modLoc(type.getTextureName("side")));
        simpleBlock(type.getData().blockTrim.get(), blockTrim);

        standardDrawer(type, type.getData().blockFull1.get(),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("front_1")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("trim")),
                1, false);
        standardDrawer(type, type.getData().blockFull2.get(),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("front_2")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("trim")),
                2, false);
        standardDrawer(type, type.getData().blockFull4.get(),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("front_4")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("trim")),
                4, false);

        standardDrawer(type, type.getData().blockHalf1.get(),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("front_1")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side_h")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("trim")),
                1, true);
        standardDrawer(type, type.getData().blockHalf2.get(),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("front_2")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side_h")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("trim")),
                2, true);
        standardDrawer(type, type.getData().blockHalf4.get(),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("front_4")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side_h")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("side")),
                new ResourceLocation(GregTechStorageDrawers.MODID, type.getTextureName("trim")),
                4, true);
    }

    void standardDrawer(DrawerTypes type, BlockStandardDrawers block, ResourceLocation side, ResourceLocation front,
                        ResourceLocation trim, int size, boolean half) {
        standardDrawer(type, block, side, front, side, side, trim, size, half);
    }

    void standardDrawer(DrawerTypes type, BlockStandardDrawers block, ResourceLocation side, ResourceLocation front,
                        ResourceLocation top, ResourceLocation back, ResourceLocation trim, int size, boolean half) {
        String parentType = half ? "half" : "full";
        ResourceLocation parent = new ResourceLocation(StorageDrawers.MOD_ID,
                "block/" + parentType + "_drawers_orientable");

        ModelFile model = models()
                .withExistingParent(type.getDrawerModelName(size, half), parent)
                .texture("particle", front)
                .texture("east", side)
                .texture("west", side)
                .texture("north", front)
                .texture("up", top)
                .texture("down", top)
                .texture("south", back)
                .texture("trim", trim);

        drawerState(block, model);
    }

    void drawerState(BlockStandardDrawers block, ModelFile model) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.partialState().with(BlockDrawers.FACING, Direction.NORTH).addModels(new ConfiguredModel(model));
        builder.partialState().with(BlockDrawers.FACING, Direction.EAST)
                .addModels(new ConfiguredModel(model, 0, 90, false));
        builder.partialState().with(BlockDrawers.FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(model, 0, 180, false));
        builder.partialState().with(BlockDrawers.FACING, Direction.WEST)
                .addModels(new ConfiguredModel(model, 0, 270, false));
    }
}
