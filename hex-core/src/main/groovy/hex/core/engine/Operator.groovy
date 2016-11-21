package hex.core.engine

/**
 * Created by poundex on 17/11/16.
 */
abstract class Operator implements Actuator
{
	@Override
	int getMinimumStackSize()
	{
		return 2
	}

	static Object popAndUnwrap(Deque<Object> stack)
	{
		Object o = stack.pop()
		if (o instanceof StackElement)
			return o.value
		else
			return o
	}
}
