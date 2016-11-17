package hex.core.engine

/**
 * Created by poundex on 17/11/16.
 */
interface Actuator
{
	int getMinimumStackSize()
	void doWithStack(Deque<Object> stack)
}