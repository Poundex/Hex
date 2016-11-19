package hex.core.util

/**
 * Created by poundex on 18/11/16.
 */
@HexSafe
class BasicFunctions
{
	@RequiresStackSize(1)
	static void smax(Deque<Object> stack)
	{
		stack.push(stack.iterator().max())
	}

	@RequiresStackSize(1)
	static void smin(Deque<Object> stack)
	{
		stack.push(stack.iterator().min())
	}

	@RequiresStackSize(1)
	static void clr(Deque<Object> stack)
	{
		stack.clear()
	}
}
