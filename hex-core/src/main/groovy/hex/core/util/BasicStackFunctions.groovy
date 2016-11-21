package hex.core.util
/**
 * Created by poundex on 18/11/16.
 */
@HexSafe
class BasicStackFunctions implements StackManipulator
{
	@Operator('')
	@RequiresStackSize(1)
	static void dup(Deque<Object> stack)
	{
		stack.push(stack.peek())
	}

	@Operator('<')
	@RequiresStackSize(1)
	static void drop(Deque<Object> stack)
	{
		stack.pop()
	}

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
}
