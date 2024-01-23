package mc.recraftors.unruled_api.widgets;


import com.google.common.collect.Lists;
import mc.recraftors.unruled_api.utils.ClientProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Accessible shadow clone of the vanilla gamerule widget.
 * Allows for easy display of custom gamerule types.
 */
@Environment(EnvType.CLIENT)
public abstract class NamedRuleWidget extends EditGameRulesScreen.AbstractRuleWidget {
    private final List<OrderedText> name;
    protected final List<ClickableWidget> children;
    private final EditGameRulesScreen screen;

    @SuppressWarnings("resource")
    protected NamedRuleWidget(List<OrderedText> description, Text name, EditGameRulesScreen screen) {
        super(description);
        this.children = Lists.newArrayList();
        this.name = ((ClientProvider)screen).unruled_getClient().textRenderer.wrapLines(name, 175);
        this.screen = screen;
    }

    public EditGameRulesScreen getScreen() {
        return screen;
    }

    @Override
    public List<? extends Element> children() {
        return children;
    }

    @Override
    public List<? extends Selectable> selectableChildren() {
        return children;
    }

    @SuppressWarnings("resource")
    protected void drawName(MatrixStack matrices, int x, int y) {
        if (this.name.size() == 1) {
            ((ClientProvider)screen).unruled_getClient().textRenderer.draw(matrices, this.name.get(0), y, (x + 5), 0xFFFFFF);
        } else if (this.name.size() >= 2) {
            ((ClientProvider)screen).unruled_getClient().textRenderer.draw(matrices, this.name.get(0), y, x, 0xFFFFFF);
            ((ClientProvider)screen).unruled_getClient().textRenderer.draw(matrices, this.name.get(1), y, (x + 10), 0xFFFFFF);
        }
    }
}
