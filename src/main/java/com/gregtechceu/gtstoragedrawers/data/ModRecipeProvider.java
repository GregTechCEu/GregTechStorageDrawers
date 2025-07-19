package com.gregtechceu.gtstoragedrawers.data;

import com.gregtechceu.gtstoragedrawers.DrawerTypes;
import com.gregtechceu.gtstoragedrawers.GregTechStorageDrawers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        for (DrawerTypes type : DrawerTypes.values()) {
            makeTrim(type, consumer);
            makeDrawer1(type, true, consumer);
            makeDrawer2(type, true, consumer);
            makeDrawer4(type, true, consumer);
            makeDrawer1(type, false, consumer);
            makeDrawer2(type, false, consumer);
            makeDrawer4(type, false, consumer);
        }
    }

    private void makeTrim(DrawerTypes type, Consumer<FinishedRecipe> consumer) {
        if (type.getPlankResource() == null)
            return;

        Item plank = ForgeRegistries.ITEMS.getValue(type.getPlankResource());
        if (plank == null)
            return;

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, type.getData().blockTrim.get(), 4)
                .pattern("x/x")
                .pattern("/x/")
                .pattern("x/x")
                .define('x', plank).define('/', Tags.Items.RODS_WOODEN)
                .group(GregTechStorageDrawers.MODID)
                .unlockedBy("has_item", has(plank))
                .save(consumer);
    }

    private void makeDrawer1(DrawerTypes type, boolean half, Consumer<FinishedRecipe> consumer) {
        ResourceLocation woodResource = half ? type.getSlabResource() : type.getPlankResource();
        if (woodResource == null)
            return;

        Item plank = ForgeRegistries.ITEMS.getValue(woodResource);
        if (plank == null)
            return;

        Block block = half ? type.getData().blockHalf1.get() : type.getData().blockFull1.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block, 1)
                .pattern("///")
                .pattern(" x ")
                .pattern("///")
                .define('x', Tags.Items.CHESTS_WOODEN).define('/', plank)
                .group(GregTechStorageDrawers.MODID)
                .unlockedBy("has_item", has(Tags.Items.CHESTS_WOODEN))
                .save(consumer);
    }

    private void makeDrawer2(DrawerTypes type, boolean half, Consumer<FinishedRecipe> consumer) {
        ResourceLocation woodResource = half ? type.getSlabResource() : type.getPlankResource();
        if (woodResource == null)
            return;

        Item plank = ForgeRegistries.ITEMS.getValue(woodResource);
        if (plank == null)
            return;

        Block block = half ? type.getData().blockHalf2.get() : type.getData().blockFull2.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block, 2)
                .pattern("/x/")
                .pattern("///")
                .pattern("/x/")
                .define('x', Tags.Items.CHESTS_WOODEN).define('/', plank)
                .group(GregTechStorageDrawers.MODID)
                .unlockedBy("has_item", has(Tags.Items.CHESTS_WOODEN))
                .save(consumer);
    }

    private void makeDrawer4(DrawerTypes type, boolean half, Consumer<FinishedRecipe> consumer) {
        ResourceLocation woodResource = half ? type.getSlabResource() : type.getPlankResource();
        if (woodResource == null)
            return;

        Item plank = ForgeRegistries.ITEMS.getValue(woodResource);
        if (plank == null)
            return;

        Block block = half ? type.getData().blockHalf4.get() : type.getData().blockFull4.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block, 4)
                .pattern("x/x")
                .pattern("///")
                .pattern("x/x")
                .define('x', Tags.Items.CHESTS_WOODEN).define('/', plank)
                .group(GregTechStorageDrawers.MODID)
                .unlockedBy("has_item", has(Tags.Items.CHESTS_WOODEN))
                .save(consumer);
    }
}
