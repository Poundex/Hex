package hex.ui.jfx.layout

import com.jfoenix.controls.JFXListView
import hex.core.context.CalculatorContext
import hex.core.context.DefaultCalculatorContext

/**
 * Created by poundex on 16/11/16.
 */
class Page extends JFXListView
{
	private final CalculatorContext calculatorContext = new DefaultCalculatorContext()

	Page()
	{
		getStyleClass() << 'pageview'
	}

	void submit(String input)
	{
		calculatorContext.pushParsed(input)
		items.setAll(calculatorContext.listView.values())
	}
}
