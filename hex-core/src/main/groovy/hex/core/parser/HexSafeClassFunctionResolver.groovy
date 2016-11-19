package hex.core.parser

import hex.core.engine.Actuator
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
//		super(klass)
		this.klass = klass
	}

	@Override
	Actuator resolve(String name)
	{
		Method foundMethod = klass.methods.find { it.name == name }
		if ( ! foundMethod)
			return null

		return new Actuator() {
			@Override
			int getMinimumStackSize()
			{
				foundMethod.getAnnotation(RequiresStackSize)?.value() ?: 0
			}

			@Override
			void doWithStack(Deque<Object> stack)
			{
				foundMethod.invoke(null, stack)
			}
		}
	}
}
