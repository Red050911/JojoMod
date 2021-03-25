package net.redstoneboy0509.mod.gift.jojomod.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.redstoneboy0509.mod.gift.jojomod.blocks.JojonicTableBlock;
import net.redstoneboy0509.mod.gift.jojomod.container.JojonicTableContainer;
import net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic.JojonicSigilRecipe;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModRecipes;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModTileEntityTypes;
import net.redstoneboy0509.mod.gift.jojomod.util.JojoItemHandler;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JojonicSigilTableTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public static final ITextComponent DEFAULT_NAME = new TranslationTextComponent("container.rjm.jojonic_table");
    public static final int MAX_WORK_TIME = 100;

    private JojoItemHandler inventory;
    private ITextComponent displayName;
    public int workTime;

    public JojonicSigilTableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        this.inventory = new JojoItemHandler(3);
    }

    public JojonicSigilTableTileEntity() {
        this(ModTileEntityTypes.JOJONIC_TABLE.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return displayName != null ? displayName : DEFAULT_NAME;
    }

    @Nullable
    @Override
    public Container createMenu(final int windowId, final PlayerInventory playerInv, final PlayerEntity playerIn) {
        return new JojonicTableContainer(windowId, playerInv, this);
    }

    @Override
    public void tick() {
        boolean dirty = false;

        if(this.world != null && !this.world.isRemote) {
            if(this.getRecipe(inventory.getStackInSlot(0)) != null) {
                if(this.workTime != MAX_WORK_TIME) {
                    this.world.setBlockState(this.getPos(), this.getBlockState().with(JojonicTableBlock.ACTIVE, true));
                    this.workTime++;
                    dirty = true;
                } else {
                    this.world.setBlockState(this.getPos(), this.getBlockState().with(JojonicTableBlock.ACTIVE, false));
                    this.workTime = 0;
                    ItemStack output = this.getRecipe(inventory.getStackInSlot(0)).getRecipeOutput();
                    ItemStack byproduct = this.getRecipe(inventory.getStackInSlot(0)).getByproduct();
                    this.inventory.insertItem(1, output.copy(), false);
                    this.inventory.insertItem(2, byproduct.copy(), false);
                    this.inventory.decrStackSize(0, 1);
                    dirty = true;
                }
            }
        }

        if(dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if(compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.displayName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
        }
        ItemStackHelper.loadAllItems(compound, this.inventory.toNonNullList());
        this.workTime = compound.getInt("WorkTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(this.displayName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(displayName));
        }
        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("WorkTime", this.workTime);

        return compound;
    }

    @Nullable
    private JojonicSigilRecipe getRecipe(ItemStack stack) {
        if (stack == null) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(ModRecipes.JOJONIC_TYPE, this.world);
        for (IRecipe<?> iRecipe : recipes) {
            JojonicSigilRecipe recipe = (JojonicSigilRecipe) iRecipe;
            if (recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
                return recipe;
            }
        }

        return null;
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }

    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
        Set<ItemStack> inputs = new HashSet<ItemStack>();
        Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> {
                for (ItemStack stack : ingredient.getMatchingStacks()) {
                    inputs.add(stack);
                }
            });
        }
        return inputs;
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundNBT nbt) {
        this.read(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
    }

    public void setCustomName(ITextComponent displayName) {
        this.displayName = displayName;
    }

    public void clearCustomName() {
        this.displayName = null;
    }

}
