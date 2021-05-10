package hu.bendi.lobby.fake;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerRecipeBook;
import net.minecraft.util.Identifier;

import java.util.Collection;

public class FakeServerRecipeBook extends ServerRecipeBook {

    @Override
    protected void add(Identifier id) {

    }

    @Override
    protected void remove(Identifier id) {

    }

    @Override
    public void remove(Recipe<?> recipe) {

    }

    @Override
    public CompoundTag toTag() {
        return new CompoundTag();
    }

    @Override
    public void fromTag(CompoundTag tag, RecipeManager recipeManager) {

    }

    @Override
    public int unlockRecipes(Collection<Recipe<?>> recipes, ServerPlayerEntity player) {
        return recipes.size();
    }

    @Override
    public int lockRecipes(Collection<Recipe<?>> recipes, ServerPlayerEntity player) {
        return recipes.size();
    }
}
