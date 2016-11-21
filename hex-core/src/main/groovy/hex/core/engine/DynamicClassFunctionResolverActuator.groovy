package hex.core.engine

import hex.core.util.StackManipulator

import java.lang.reflect.Method

/**
 * Created by poundex on 21/11/16.
 */
class DynamicClassFunctionResolverActuator implements Actuator, StackManipulator
{
	final int minimumStackSize
	private final String name
	private final List<Method> candidates
	private final Class providerClass

	DynamicClassFunctionResolverActuator(String name, List<Method> candidates, Class providerClass)
	{
		this.name = name
		this.candidates = candidates
		this.providerClass = providerClass
		this.minimumStackSize = candidates.first().parameterTypes.size()
	}

	@Override
	void doWithStack(Deque<Object> stack)
	{
		Method foundMethod = candidates.reverse(false).findAll { it.parameterTypes.size() <= stack.size() }.find {
			providerClass.metaClass.pickMethod(name, takeN(stack, it.parameterTypes.size())*.class as Class[])
		}

		if ( ! foundMethod)
			return

		List<Object> params = []
		foundMethod.parameterTypes.each { params << popAndUnwrap(stack).asType(it) }
		stack.push foundMethod.invoke(null, *params.reverse(false))
	}
}
