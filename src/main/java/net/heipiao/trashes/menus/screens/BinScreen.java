package net.heipiao.trashes.menus.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.heipiao.trashes.menus.BinMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BinScreen extends AbstractContainerScreen<BinMenu> {
    private final int textureWidth = 176;
    private final int textureHeight = 166;
    private static final ResourceLocation BG=new ResourceLocation("trashes", "textures/gui/bin.png");
    public BinScreen(BinMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack stack, float p_97788_, int p_97789_, int p_97790_) {
        this.renderBackground(stack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG);
        int i = this.leftPos;
        int j = this.topPos;
        blit(stack, i, j, 0, 0, imageWidth, imageHeight);
    }
}
