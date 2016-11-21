package hex.core.parser

import hex.core.engine.Actuator
import hex.core.engine.DynamicClassFunctionResolverActuator
import hex.core.util.StackManipulator

import java.lang.reflect.Method

/**
 * Created by poundex on 18/11/16.
 */
class DefaultClassFunctionResolver implements ClassFunctionResolver, StackManipulator
{
	protected final Class klass

	DefaultClassFunctionResolver(Class klass)
	{
		this.klass = klass
	}

	@Override
	Actuator resolve(String name)
	{
		List<Method> candidates = klass.methods.findAll { it.name == name }.sort { it.parameterTypes.size() }

		if ( ! candidates)
			return null

		return new DynamicClassFunctionResolverActuator(name, candidates, klass)
	}
}
