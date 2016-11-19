package hex.core.parser

import hex.core.engine.Actuator

/**
 * Created by poundex on 18/11/16.
 */
interface ClassFunctionResolver
{
	Actuator resolve(String name)
}
