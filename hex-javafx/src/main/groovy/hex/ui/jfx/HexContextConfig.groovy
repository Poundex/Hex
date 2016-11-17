package hex.ui.jfx

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import groovy.transform.CompileStatic
import groovyx.javafx.SceneGraphBuilder
import hex.ui.jfx.layout.MainStage
import hex.ui.jfx.layout.Page
import hex.ui.jfx.layout.PageManager
import javafx.stage.Stage
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope

/**
 * Created by poundex on 16/11/16.
 */
@CompileStatic
@Configuration @Lazy
class HexContextConfig
{
	@Bean
	MainStage mainStage()
	{
		return new MainStage()
	}

	@Bean
	SceneGraphBuilder sceneGraphBuilder(Stage primaryStage)
	{
		SceneGraphBuilder sceneGraphBuilder = new SceneGraphBuilder(primaryStage)
		sceneGraphBuilder.registerBeanFactory('jfxList', JFXListView)
		sceneGraphBuilder.registerBeanFactory('jfxTextField', JFXTextField)
		return  sceneGraphBuilder
	}

	@Bean
	PageManager pageManager()
	{
		return new PageManager()
	}

	@Bean @Scope(BeanDefinition.SCOPE_PROTOTYPE)
	Page page()
	{
		return new Page()
	}
}
