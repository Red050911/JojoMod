package net.redstoneboy0509.mod.gift.jojomod.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.redstoneboy0509.mod.gift.jojomod.container.JojoContainer;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModItems;
import net.redstoneboy0509.mod.gift.jojomod.util.Util;

import javax.annotation.Nullable;
import java.util.function.Function;

public class JojoEntity extends LootStoringTameable {

    private NonNullList<ItemStack> chestContents = NonNullList.withSize(9, ItemStack.EMPTY);
    private IItemHandlerModifiable items = new InvWrapper(this);
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    public JojoEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return chestContents;
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity playerIn) {
        return new JojoContainer(windowId, playerInv, this);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if(!this.world.isRemote && stack.getItem() == ModItems.JOJO_FOOD.get()) {
            this.consumeItemFromStack(player, stack);
            this.heal(4.0f);
            if(!this.isTamed()) {
                this.setTamedBy(player);
                player.sendMessage(new TranslationTextComponent("message.rjm.tame_jojo_self"));
            }
            return true;
        }
        if(!this.world.isRemote && stack.getItem() == ModItems.JOJONIC_SIGIL.get()) {
            this.consumeItemFromStack(player, stack);
            player.addItemStackToInventory(new ItemStack(ModItems.ACTIVE_JOJONIC_SIGIL.get()));
            return true;
        }
        if(!this.world.isRemote) {
            if(this.getOwner() == null || this.getOwnerId() == null) {
                return false;
            }
            if(player.getUniqueID().toString().equals(this.getOwnerId().toString()) && player.isCrouching()) {
                Util.playerEntityOpenJojoInventory(player, this);
            }
            if(player.getUniqueID().toString().equals(this.getOwnerId().toString())) {
                this.sitGoal.setSitting(!this.isSitting());
                return true;
            }
        }
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.sitGoal = new SitGoal(this);
        goalSelector.addGoal(1, new LookRandomlyGoal(this));
        goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.5f));
        goalSelector.addGoal(2, sitGoal);
        goalSelector.addGoal(3, new SwimGoal(this));
        goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0f, false));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        //Register extra attributes

        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);

        //Set extra attributes

        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
        this.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1);

        //Set Existing

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0f);
    }

    @Override
    public void livingTick() {
        super.livingTick();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        ItemStackHelper.loadAllItems(compound, this.chestContents);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        ItemStackHelper.saveAllItems(compound, this.chestContents);
    }

}
