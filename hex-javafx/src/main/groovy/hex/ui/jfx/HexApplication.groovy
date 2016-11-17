package hex.ui.jfx

import groovy.transform.CompileStatic
import hex.ui.jfx.layout.MainStage
import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * Created by poundex on 16/11/16.
 */
@CompileStatic
class HexApplication extends Application
{
	public static void main(String[] args)
	{
		launch(HexApplication)
	}

	@Override
	void start(Stage primaryStage) throws Exception
	{
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HexContextConfig)
		ctx.beanFactory.registerSingleton('primaryStage', primaryStage)
		ctx.getBean(MainStage)
	}
}
