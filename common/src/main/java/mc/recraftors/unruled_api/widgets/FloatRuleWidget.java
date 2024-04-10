package mc.recraftors.unruled_api.widgets;

import mc.recraftors.unruled_api.rules.FloatRule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FloatRuleWidget extends NamedRuleWidget {
    private final TextFieldWidget valueWidget;

    public FloatRuleWidget(Text name, @Nullable List<OrderedText> description, String ruleName, FloatRule rule, EditGameRulesScreen screen) {
        super(description, name, screen);
        this.valueWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 10, 5, 42, 20, name.copy().append("\n").append(ruleName).append("\n"));
        this.valueWidget.setText(rule.serialize());
        this.valueWidget.setChangedListener(val -> {
            if (rule.validate(val)) {
                this.valueWidget.setEditableColor(0xE0E0E0);
                screen.markValid(this);
            } else {
                this.valueWidget.setEditableColor(0xFF0000);
                screen.markInvalid(this);
            }
        });
        this.children.add(this.valueWidget);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(matrices, y, x);
        this.valueWidget.x = x + entryWidth - 44;
        this.valueWidget.y = y;
        this.valueWidget.render(matrices, mouseX, mouseY, tickDelta);
    }
}
