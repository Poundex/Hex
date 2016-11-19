package hex.ui.jfx.layout

import com.jfoenix.controls.JFXListView
import hex.core.context.CalculatorContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by poundex on 16/11/16.
 */
@Component @Scope(BeanDefinition.SCOPE_PROTOTYPE)
class Page extends JFXListView
{
	private final CalculatorContext calculatorContext

	@Autowired
	Page(CalculatorContext calculatorContext)
	{
		this.calculatorContext = calculatorContext
		getStyleClass() << 'pageview'
	}

	void submit(String input)
	{
		calculatorContext.pushParsed(input)
		items.setAll(calculatorContext.listView.values())
	}
}
