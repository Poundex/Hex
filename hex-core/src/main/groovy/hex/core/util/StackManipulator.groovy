package hex.core.util

import hex.core.engine.StackElement

/**
 * Created by poundex on 21/11/16.
 */
trait StackManipulator
{
	static Object popAndUnwrap(Deque<Object> stack)
	{
		Object o = stack.pop()
		if (o instanceof StackElement)
			return o.value
		else
			return o
	}

	static unwrapAll(Deque<Object> stack)
	{
		return stack.collect {
			it instanceof StackElement ? it.value : it
		}
	}

	static List<Object> takeN(Deque<Object> stack, int n)
	{
		return stack.take(n).collect { it instanceof StackElement ? it.value : it }
	}
}
