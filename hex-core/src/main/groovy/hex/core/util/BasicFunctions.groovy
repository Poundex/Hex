package hex.core.util

import hex.core.engine.StackElement

/**
 * Created by poundex on 18/11/16.
 */
@HexSafe
class BasicFunctions
{
	@RequiresStackSize(1)
	static void smax(Deque<Object> stack)
	{
		stack.push(unwrapAll(stack).max())
	}

	@RequiresStackSize(1)
	static void smin(Deque<Object> stack)
	{
		stack.push(unwrapAll(stack).min())
	}

	@RequiresStackSize(1)
	static void clr(Deque<Object> stack)
	{
		stack.clear()
	}

	@RequiresStackSize(1)
	static void sum(Deque<Object> stack)
	{
		Object o = unwrapAll(stack).sum()
		stack.clear()
		stack.push(o)
	}

	private static unwrapAll(Deque<Object> stack)
	{
		return stack.collect {
			it instanceof StackElement ? it.value : it
		}
	}
}
