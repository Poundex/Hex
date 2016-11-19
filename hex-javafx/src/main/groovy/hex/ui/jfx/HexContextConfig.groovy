package hex.ui.jfx

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import groovy.transform.CompileStatic
import groovyx.javafx.SceneGraphBuilder
import hex.core.context.CalculatorContext
import hex.core.context.DefaultCalculatorContext
import hex.core.parser.FunctionResolver
import hex.core.parser.StringParser
import javafx.stage.Stage
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.*

/**
 * Created by poundex on 16/11/16.
 */
@CompileStatic
@ComponentScan(basePackages = 'hex.ui.jfx', lazyInit = true)
@Configuration @Lazy
class HexContextConfig
{
	@Bean
	SceneGraphBuilder sceneGraphBuilder(Stage primaryStage)
	{
		SceneGraphBuilder sceneGraphBuilder = new SceneGraphBuilder(primaryStage)
		sceneGraphBuilder.registerBeanFactory('jfxList', JFXListView)
		sceneGraphBuilder.registerBeanFactory('jfxTextField', JFXTextField)
		return  sceneGraphBuilder
	}

	@Bean @Scope(BeanDefinition.SCOPE_PROTOTYPE)
	CalculatorContext calculatorContext(StringParser stringParser)
	{
		return new DefaultCalculatorContext(stringParser)
	}

	@Bean
	StringParser stringParser(FunctionResolver functionResolver)
	{
		return new StringParser(functionResolver)
	}

	@Bean
	FunctionResolver functionResolver()
	{
		return new FunctionResolver()
	}
}
