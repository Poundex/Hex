package hex.core.engine

/**
 * Created by poundex on 17/11/16.
 */

class DuplicateStackCommand extends StackCommand
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.push(stack.peek())
	}
}

class DropStackCommand extends StackCommand
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.pop()
	}
}
