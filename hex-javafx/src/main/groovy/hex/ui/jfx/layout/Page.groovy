package hex.ui.jfx.layout

import com.jfoenix.controls.JFXListView
import groovy.transform.CompileStatic
import hex.core.context.CalculatorContext
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.layout.BorderPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by poundex on 16/11/16.
 */
@Component @Scope(BeanDefinition.SCOPE_PROTOTYPE)
class Page extends JFXListView<Tuple2<Integer, Object>>
{
	private final CalculatorContext calculatorContext

	@Autowired
	Page(CalculatorContext calculatorContext)
	{
		this.calculatorContext = calculatorContext
		getStyleClass() << 'pageview'
		setCellFactory(this.&renderCell)
	}

	void submit(String input)
	{
		calculatorContext.pushParsed(input)
		items.setAll(calculatorContext.listView.collect { new Tuple2<Integer, Object>(it.key, it.value)} )
	}

	private ListCell<Tuple2<Integer, Object>> renderCell(ListView<Tuple2<Integer, Object>> listView)
	{
		return new StackElementCell()
	}

	@CompileStatic
	static class StackElementCell extends ListCell<Tuple2<Integer, Object>>
	{
		private final Label valueLabel = new Label()
		private final Label indexLabel = new Label('0:')
		private final BorderPane borderPane = new BorderPane(null, null, valueLabel, null, indexLabel)

		StackElementCell()
		{
			super()
			indexLabel.disable = true
			setGraphic(borderPane)
		}

		@Override
		protected void updateItem(Tuple2<Integer, Object> item, boolean empty)
		{
			super.updateItem(item, empty)
			if (empty)
				return

			indexLabel.text = "${item.first}:"
			valueLabel.text = item.second.toString()
		}
	}
}
