package hex.core.parser

import hex.core.engine.Actuator
import hex.core.engine.StackElement

import java.lang.reflect.Method

import static hex.core.engine.Operator.popAndUnwrap

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
					klass.metaClass.pickMethod(name, takeN(stack, parameterTypes.size())*.class as Class[])
				}

				if ( ! foundMethod)
					return

				List<Object> params = []
				foundMethod.parameterTypes.each { params << popAndUnwrap(stack).asType(it) }
				stack.push foundMethod.invoke(null, *params.reverse(false))
			}
		}
	}

	public static List<Object> takeN(Deque<Object> stack, int n)
	{
		stack.take(n).collect { it instanceof StackElement ? it.value : it }
	}
}
