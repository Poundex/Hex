package hex.core.parser

import hex.core.engine.Actuator
import hex.core.engine.StaticClassFunctionResolver
import hex.core.util.Operator
import hex.core.util.RequiresStackSize

import java.lang.reflect.Method

/**
 * Created by poundex on 18/11/16.
 */
class HexSafeClassFunctionResolver implements ClassFunctionResolver
{
	private final Class klass

	HexSafeClassFunctionResolver(Class klass)
	{
		this.klass = klass
	}

	@Override
	Actuator resolve(String name)
	{
		Method foundMethod = klass.methods.find { it.name == name || it.getAnnotation(Operator)?.value() == name}
		if ( ! foundMethod)
			return null

		return new StaticClassFunctionResolver(foundMethod)
	}
}
