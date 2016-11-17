package hex.core.engine

/**
 * Created by poundex on 17/11/16.
 */
abstract class StackCommand implements Actuator
{
	@Override
	int getMinimumStackSize()
	{
		return 1
	}
}
