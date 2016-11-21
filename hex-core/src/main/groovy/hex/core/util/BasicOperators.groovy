package hex.core.util
/**
 * Created by poundex on 17/11/16.
 */
@HexSafe
class BasicOperators implements StackManipulator
{
	@Operator('+')
	@RequiresStackSize(2)
	static void add(Deque<Object> stack)
	{
		stack.push(popAndUnwrap(stack) + popAndUnwrap(stack))
	}

	@Operator('-')
	@RequiresStackSize(2)
	static void subtract(Deque<Object> stack)
	{
		Object o = popAndUnwrap(stack)
		stack.push(popAndUnwrap(stack) - o)
	}

	@Operator('*')
	@RequiresStackSize(1)
	static void multiply(Deque<Object> stack)
	{
		stack.push(popAndUnwrap(stack) * popAndUnwrap(stack))
	}

	@Operator('/')
	@RequiresStackSize(2)
	static void divide(Deque<Object> stack)
	{
		Object o = popAndUnwrap(stack)
		stack.push(popAndUnwrap(stack) / o)
	}
}


