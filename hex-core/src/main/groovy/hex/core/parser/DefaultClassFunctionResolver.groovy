package hex.core.parser

import hex.core.engine.Actuator

import java.lang.reflect.Method

/**
 * Created by poundex on 18/11/16.
 */
class DefaultClassFunctionResolver implements ClassFunctionResolver
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

		return new Actuator() {
			@Override
			int getMinimumStackSize()
			{
				return candidates.first().parameterTypes.size()
			}

			@Override
			void doWithStack(Deque<Object> stack)
			{
				Method foundMethod = candidates.reverse(false).findAll { it.parameterTypes.size() <= stack.size() }.find {
					klass.metaClass.pickMethod(name, [*stack.take(it.parameterTypes.size())*.class] as Class[])
				}

				if ( ! foundMethod)
					return

				List<Object> params = []
				foundMethod.parameterTypes.each { params << stack.pop().asType(it) }
				stack.push foundMethod.invoke(null, *params.reverse(false))
			}
		}
	}
}
